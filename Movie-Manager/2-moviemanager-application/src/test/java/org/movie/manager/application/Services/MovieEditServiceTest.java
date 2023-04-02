package org.movie.manager.application.Services;

import org.junit.jupiter.api.Test;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;
import org.movie.manager.domain.Movie.MovieRepository;
import org.easymock.EasyMock;
import java.util.UUID;

public class MovieEditServiceTest {
    @Test
    public void shouldSaveNewMovie() {
        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        Movie movie = new Movie(null, null, null, 0, 0, null, null, null, null);
        movieRepository.update(movie);
        EasyMock.replay(movieRepository);

        MovieEditService codeUnderTest = new MovieEditService(movieRepository, null, null);
        codeUnderTest.saveNewMovie(movie, null, null);

        EasyMock.verify(movieRepository);
    }

    @Test
    public void shouldUpdateMovie() {
        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        MovieID movieID = new MovieID(UUID.randomUUID());
        Movie movie = new Movie(movieID, "created", null, 0, 0, null, null, null, null);
        Movie movie2 = new Movie(movieID, "edited", null, 0, 0, null, null, null, null);
        movieRepository.update(movie);
        movieRepository.update(movie2);
        EasyMock.replay(movieRepository);

        MovieEditService codeUnderTest = new MovieEditService(movieRepository, null, null);
        codeUnderTest.saveNewMovie(movie, null, null);
        codeUnderTest.saveNewMovie(movie2, null, null);

        EasyMock.verify(movieRepository);

    }

}
