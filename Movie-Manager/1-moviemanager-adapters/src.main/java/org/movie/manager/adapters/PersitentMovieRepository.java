package org.movie.manager.adapters;

import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
import java.util.UUID;

public class PersitentMovieRepository implements MovieRepository {
    @Override
    public Collection<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovie(UUID movieID) {
        return null;
    }

    @Override
    public void remove(UUID movieID) {

    }

    @Override
    public void update(Movie movie) {

    }
}
