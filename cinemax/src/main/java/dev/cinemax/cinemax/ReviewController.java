package dev.cinemax.cinemax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(){
        return new ResponseEntity<List<Reviews>>(reviewService.allReviews(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reviews> createReview(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Reviews>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

}
