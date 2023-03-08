package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.CSVDatabase;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
import java.util.UUID;

public class PersistentMovieRepository implements MovieRepository {

    private final GenericEntityManager entityManager;
    private CSVDatabase csvDB;

    public PersistentMovieRepository(GenericEntityManager entityManager, CSVDatabase csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
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
    public void update(Movie movie) {
        entityManager.remove(entityManager.find(Movie.class, movie.getMovieID()));
        try {
            entityManager.persist(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
