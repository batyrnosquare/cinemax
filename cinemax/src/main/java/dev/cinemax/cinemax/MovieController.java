package dev.cinemax.cinemax;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies(){
        return new ResponseEntity<List<Movies>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movies>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movies>>(movieService.singleMovie(imdbId), HttpStatus.OK);
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
