package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
import java.util.UUID;

public class PersistentMovieRepository implements MovieRepository {

    private final GenericEntityManager entityManager;

    public PersistentMovieRepository(GenericEntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Collection<Movie> getAllMovies() {
        return entityManager.find(Movie.class);
    }

    @Override
    public Movie getMovie(UUID movieID) {
        return (Movie)entityManager.find(Movie.class, movieID);
    }

    @Override
    public void remove(UUID movieID) {

    }

    @Override
    public void update(Movie movie) {

    }
}
