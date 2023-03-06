package org.movie.manager.application;
import org.movie.manager.domain.Movie;
import org.movie.manager.domain.Availability;
import org.movie.manager.domain.MovieRepository;

import java.util.Collection;
import java.util.UUID;

public class FilmService {

    private final MovieRepository repository;

    public FilmService(MovieRepository repository) {
        super();
        this.repository = repository;
    }

    public Collection<Movie> getAllMovies() {
        return this.repository.getAllMovies();
    }
    public Movie getMovie(UUID movieID){
        return this.repository.getMovie(movieID);
    }

    public void setOwnRating(UUID movieID, int ownRating){
        Movie movie = this.getMovie(movieID);
        if(movie == null){ //
            throw new IllegalArgumentException("Movie was not found");
        }
        movie.setOwnRating(ownRating);
        this.repository.update(movie);
    }
    public void setAvailability(UUID movieID, Availability availability){
        Movie movie = this.getMovie(movieID);
        if(movie == null){ //
            throw new IllegalArgumentException("Movie was not found");
        }
        movie.setAvailability(availability);
        this.repository.update(movie);
    }
}
