package dev.cinemax.cinemax.controller;


import dev.cinemax.cinemax.dto.ReqRes;
import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequset){
        return ResponseEntity.ok(userService.signUp(signUpRequset));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequset){
        return ResponseEntity.ok(userService.signIn(signInRequset));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(userService.refreshToken(refreshTokenRequest));
    }

//    @PostMapping
//    public String addUser(@RequestBody User user){
//        userService.addUser(user);
//        return "User is saved";
//    }

}
