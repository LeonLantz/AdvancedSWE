package org.movie.manager.application.Services;

import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.MovieRepository;

public class MovieEditService {

    private final MovieRepository movieRepository;
    private final MetadataRepository metadataRepository;
    private final FilmProfessionalRepository filmProfessionalRepository;

    public MovieEditService(MovieRepository movieRepository, MetadataRepository metadataRepository, FilmProfessionalRepository filmProfessionalRepository) {
        this.movieRepository = movieRepository;
        this.metadataRepository = metadataRepository;
        this.filmProfessionalRepository = filmProfessionalRepository;
    }

    // TODO generated update getMoviesWithFilter
//    public void update(Movie movie) {
//        this.movieRepository.update(movie);
//    }

}
