package org.movie.manager.application.Services;
import org.movie.manager.domain.Metadaten.Rating;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Metadaten.Availability;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
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
    public Movie getMovie(UUID movieID){
        return this.repository.getMovie(movieID);
    }

    public void setOwnRating(UUID movieID, Rating ownRating){
        Movie movie = this.getMovie(movieID);
        if(movie == null){ //
            throw new IllegalArgumentException("Movie was not found");
        }
        movie.getMetadata().setOwnRating(ownRating);
        this.repository.update(movie);
    }
    public void setAvailability(UUID movieID, Availability availability){
        Movie movie = this.getMovie(movieID);
        if(movie == null){ //
            throw new IllegalArgumentException("Movie was not found");
        }
        movie.getMetadata().setAvailability(availability);
        this.repository.update(movie);
    }
}
