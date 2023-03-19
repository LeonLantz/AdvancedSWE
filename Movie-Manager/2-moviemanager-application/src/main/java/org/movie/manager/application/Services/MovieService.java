package org.movie.manager.application.Services;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        super();
        this.repository = repository;
    }

    public Collection<Movie> getAllMovies() {
        return this.repository.getAllMovies();
    }
    public Optional<Movie> getMovie(UUID movieID){
        return this.repository.getMovie(movieID);
    }
}
