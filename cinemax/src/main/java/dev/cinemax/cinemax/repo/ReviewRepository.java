package dev.cinemax.cinemax.repo;

import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.entity.Reviews;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Reviews, ObjectId> {
    List<Reviews> findByImdbId(String imdbId);
}
