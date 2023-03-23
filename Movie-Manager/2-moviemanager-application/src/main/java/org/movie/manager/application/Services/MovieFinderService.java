package org.movie.manager.application.Services;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;
import org.movie.manager.domain.Persistable;

import java.util.*;

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
    public Collection<Persistable> getAllMovieData(UUID movieID, UUID metadataID){
        Collection allMovieData = new ArrayList();
        allMovieData.add(this.movieRepository.getMovie(movieID).get());
        allMovieData.add(this.metadataRepository.getMetadata(metadataID).get());
        allMovieData.add(filmProfessionalRepository.getFilmProfessionalsOfMovie(movieID));
        return allMovieData;
    }
    public Collection<Movie> getMoviesWithFilter(List<Filter> filters) {
        Collection<Movie> filteredMovies = new ArrayList<>();
        Collection<Movie> allMovies = this.getAllMovies();

        for (Movie movie : allMovies) {
            boolean passFilter = true;
            for (Filter filter : filters) {
                Optional<Metadata> metadataOptional = metadataRepository.getMetadata(movie.getMetadataID().getMetadataID());
                Metadata metadata;
                if(metadataOptional != null) {
                    metadata = metadataOptional.get();

                    switch (filter.getName()) {
                        case "ownratingBigger":
                            passFilter &= metadata.getOwnRating().getRating() >= (int) filter.getValue();
                            break;
                        case "ownratingSmaller":
                            passFilter &= metadata.getOwnRating().getRating() <= (int) filter.getValue();
                            break;
                        case "ownership":
                            passFilter &= metadata.getAvailability().getOwnership().toString() == filter.getValue();
                            break;
                        default:
                            break;
                    }
                    if (!passFilter) {
                        break;
                    }
                }
            }
            if (passFilter) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }
}
