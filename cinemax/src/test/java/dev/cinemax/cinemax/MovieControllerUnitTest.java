package dev.cinemax.cinemax;

import dev.cinemax.cinemax.controller.MovieController;
import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerUnitTest {

    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;

    @Test
    public void testGetMoviesByGenre_Success() {
        String genre = "Action";
        List<Movies> expectedMovies = new ArrayList<>();

        Mockito.when(movieService.getMoviesByGenre(genre)).thenReturn(expectedMovies);

        ResponseEntity<List<Movies>> response = movieController.getMoviesByGenre(genre);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMovies, response.getBody());
    }

    @Test
    public void testGetMoviesByGenre_NotFound() {
        String genre = "Sci-Fi";
        Mockito.when(movieService.getMoviesByGenre(genre)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Movies>> response = movieController.getMoviesByGenre(genre);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}