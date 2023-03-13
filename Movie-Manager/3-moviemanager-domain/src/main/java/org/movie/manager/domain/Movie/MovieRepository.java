package org.movie.manager.domain.Movie;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {
    Iterable<Movie> getAllMovies();

    Optional<Movie> getMovie(UUID movieID);

    void update(Movie movie);
}
