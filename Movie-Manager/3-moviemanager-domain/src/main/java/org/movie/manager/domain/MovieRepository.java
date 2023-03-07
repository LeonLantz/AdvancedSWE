package org.movie.manager.domain;
import java.util.Collection;
import java.util.UUID;

public interface MovieRepository {
    Collection<Movie> getAllMovies();

    Movie getMovie(UUID movieID);

    void remove(UUID movieID);

    void update(Movie movie);
}
