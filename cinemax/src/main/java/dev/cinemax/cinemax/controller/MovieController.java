package dev.cinemax.cinemax.controller;
import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.service.MovieService;
import dev.cinemax.cinemax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;


    @GetMapping("/usersnames")
    public ResponseEntity<List<User>> getAllUsernames(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies(){
        return new ResponseEntity<>(movieService.allMovies(), HttpStatus.OK);
    }


    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movies>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<>(movieService.singleMovie(imdbId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movies> createMovie(@RequestBody Movies movie) {
        Movies createdMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }
    @PutMapping("/{imdbId}")
    public ResponseEntity<Movies> updateMovie(@PathVariable String imdbId, @RequestBody Movies updatedMovie) {
        Movies movie = movieService.updateMovie(imdbId, updatedMovie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String imdbId) {
        movieService.deleteMovie(imdbId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
