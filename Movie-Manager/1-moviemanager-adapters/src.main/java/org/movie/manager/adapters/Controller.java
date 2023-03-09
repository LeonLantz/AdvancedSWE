package org.movie.manager.adapters;

import org.movie.manager.application.Services.FilmProfessionalService;
import org.movie.manager.application.Services.MetadataService;
import org.movie.manager.application.Services.MovieService;

public class Controller {
    private MovieService movieService;
    private FilmProfessionalService filmProfessionalService;
    private MetadataService metadataService;

    private IMDBapi imbdAPI;

    public Controller(MovieService movieService, FilmProfessionalService filmProfessionalService, MetadataService metadataService, IMDBapi imbdAPI) {
        this.movieService = movieService;
        this.filmProfessionalService = filmProfessionalService;
        this.metadataService = metadataService;
        this.imbdAPI = imbdAPI;
    }
}
