package dev.cinemax.cinemax.controller;
import dev.cinemax.cinemax.entity.Reviews;
import dev.cinemax.cinemax.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(){
        return new ResponseEntity<>(reviewService.allReviews(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<List<Reviews>> getReviewsForMovie(@PathVariable String imdbId){
        List<Reviews> reviewsByImdbId = reviewService.getReviewsByImdbId(imdbId);
        return new ResponseEntity<>(reviewsByImdbId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reviews> createReview(@RequestBody Map<String, String> payload){
        return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

}
