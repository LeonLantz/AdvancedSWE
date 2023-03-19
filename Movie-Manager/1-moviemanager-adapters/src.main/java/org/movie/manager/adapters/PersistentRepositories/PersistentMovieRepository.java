package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.Database;
import org.movie.manager.adapters.Mapper.MovieMapper;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.*;

public class PersistentMovieRepository implements MovieRepository {

    private final GenericEntityManager entityManager;
    private Database csvDB;

    public PersistentMovieRepository(GenericEntityManager entityManager, Database csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
    }


    @Override
    public Collection<Movie> getAllMovies() {
        return entityManager.find(Movie.class);
    }

    @Override
    public Optional<Movie> getMovie(UUID movieID) {
        return Optional.of((Movie)entityManager.find(Movie.class, movieID));
    }

    @Override
    public void update(Movie movie) {
        entityManager.remove(entityManager.find(Movie.class, movie.getMovieID()));
        try {
            entityManager.persist(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Save
        MovieMapper csvMovieMapper = new MovieMapper();
        List<Object[]> csvDataMovie = new ArrayList<>();
        List<Movie> alleMovies = this.entityManager.find( Movie.class );
        alleMovies.forEach( e -> csvDataMovie.add( (csvMovieMapper.mapData(e) )));
        csvDB.saveData(csvDataMovie, MovieMapper.getHeader());
    }
}
