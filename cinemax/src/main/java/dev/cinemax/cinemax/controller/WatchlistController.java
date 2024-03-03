package dev.cinemax.cinemax.controller;


import dev.cinemax.cinemax.dto.ReqRes;
import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.repo.UserRepository;
import dev.cinemax.cinemax.service.MovieService;
import dev.cinemax.cinemax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addWatchlist")
    public ResponseEntity<String> addToWatchlist(@RequestBody String imdbId){
        Optional<User> userOptional = getUserFromContext();
        User user = userOptional.orElse(null);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Movies> moviesOptional = movieService.singleMovie(imdbId);


        if (!user.getWatchlist().contains(imdbId)){
            user.getWatchlist().add(imdbId);
            return new ResponseEntity<>("Movie added to watchlist", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Movie already in watchlist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getWatchlist")
    public ResponseEntity<List<Movies>> getWatchlist() {
        Optional<User> userOptional = getUserFromContext();
        User user = userOptional.orElse(null);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Movies> watchlistMovies = new ArrayList<>();
        for (String imdbId : user.getWatchlist()) {
            Optional<Movies> moviesOptional = movieService.singleMovie(imdbId);
            Movies movies = moviesOptional.orElse(null);
            if (movies != null) {
                watchlistMovies.add(movies);
            }
        }
        return new ResponseEntity<>(watchlistMovies, HttpStatus.OK);
    }

    private Optional<User> getUserFromContext(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getByUsername(email);
    }
}
