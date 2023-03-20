package org.movie.manager.application.Services;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MovieFinderService {

    private final MovieRepository movieRepository;
    private final MetadataRepository metadataRepository;
    private final FilmProfessionalRepository filmProfessionalRepository;

    public MovieFinderService(MovieRepository movieRepository, MetadataRepository metadataRepository, FilmProfessionalRepository filmProfessionalRepository) {
        this.movieRepository = movieRepository;
        this.metadataRepository = metadataRepository;
        this.filmProfessionalRepository = filmProfessionalRepository;
    }

    public Collection<Movie> getAllMovies() {
        return this.movieRepository.getAllMovies();
    }
    public Optional<Movie> getMovie(UUID movieID){
        return this.movieRepository.getMovie(movieID);
    }

    // TODO generated method getMoviesWithFilter
//    public Collection<Movie> getMoviesWithFilter(UUID movieID){
//        return this.movieRepository.getAllMovies();
//    }
}
