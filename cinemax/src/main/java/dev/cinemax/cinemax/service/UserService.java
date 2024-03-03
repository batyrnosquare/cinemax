package dev.cinemax.cinemax.service;

import dev.cinemax.cinemax.dto.ReqRes;
import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRoles(registrationRequest.getRoles());
            User userResult = userRepository.save(user);
            if (userResult != null) {
                resp.setUser(userResult);
                resp.setMessage("User Saved Successfully!");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS:" + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In!");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequset){
        ReqRes response = new ReqRes();
        String email = jwtUtils.extractUsername(refreshTokenRequset.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequset.getToken(), user)){
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequset.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }


    public boolean addToWatchlist(String  email, String imdbId){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "+ email));

        if (user.getWatchlist() == null){
            user.setWatchlist(new ArrayList<>());
        }

        List<String> watchlist = user.getWatchlist();
        if (watchlist.contains(imdbId)){
            return false;
        }
        watchlist.add(imdbId);

        user.setWatchlist(watchlist);
        userRepository.save(user);
        return true;
    }



}
