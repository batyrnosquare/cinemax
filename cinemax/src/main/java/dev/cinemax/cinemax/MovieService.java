package dev.cinemax.cinemax;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movies> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movies> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }
    public Movies createMovie(Movies movie){
        return movieRepository.save(movie);
    }

    public Movies updateMovie(String imdbId, Movies updatedMovie){
        Movies movie = movieRepository.findMovieByImdbId(imdbId)
                .orElseThrow(() -> new RuntimeException("Movie not found with IMDb ID: " + imdbId));

        movie.setTitle(updatedMovie.getTitle());

        return movieRepository.save(movie);
    }

    public void deleteMovie(String imdbId){
        movieRepository.findMovieByImdbId(imdbId).ifPresent(movieRepository::delete);
    }
}
