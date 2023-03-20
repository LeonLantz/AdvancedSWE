package org.movie.manager.application.Services;

import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.MetadataID;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;

import java.util.Optional;

public class MovieEditService {

    private final MovieRepository movieRepository;
    private final MetadataRepository metadataRepository;
    private final FilmProfessionalRepository filmProfessionalRepository;

    public MovieEditService(MovieRepository movieRepository, MetadataRepository metadataRepository, FilmProfessionalRepository filmProfessionalRepository) {
        this.movieRepository = movieRepository;
        this.metadataRepository = metadataRepository;
        this.filmProfessionalRepository = filmProfessionalRepository;
    }

    public void updateMovieInformations(Movie movie){
        MetadataID metadataID = movie.getMetadataID();
        Optional<Metadata> metadata = metadataRepository.getMetadata(metadataID.getMetadataID());
        if (!metadata.isEmpty()) {
            metadataRepository.update(metadata.get());
        }
        movieRepository.update(movie);
    }
    public void saveNewMovie(Movie movie, Metadata metadata, FilmProfessional filmProfessional){
        if(movieRepository != null)
            movieRepository.update(movie);
        if(metadataRepository != null)
            metadataRepository.update(metadata);
        if(filmProfessional != null)
            filmProfessionalRepository.update(filmProfessional);
    }

}
