package dev.cinemax.cinemax.repo;

import dev.cinemax.cinemax.entity.Movies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movies, ObjectId> {
    Optional<Movies> findMovieByImdbId(String imdbId);

    List<Movies> findByGenresContaining(String genre);

    List<Movies> findByTitle(String title);
}
