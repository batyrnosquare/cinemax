package dev.cinemax.cinemax.controller;


import dev.cinemax.cinemax.dto.ReqRes;
import dev.cinemax.cinemax.repo.MovieRepository;
import dev.cinemax.cinemax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-to-watchlist")
    public ResponseEntity<ReqRes> addToWatchlist(@RequestBody Map<String, String> requestBody){
        String email = requestBody.get("email");
        String imdbId = requestBody.get("imdbId");
        boolean isAdded = userService.addToWatchlist(email, imdbId);
        if(isAdded){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body(new ReqRes("Movie already exists in watchlist"));
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> getWatchlist(@RequestParam String email){
        List<String> watchlist = userService.getWatchlist(email);
        return ResponseEntity.ok(watchlist);
    }
}
