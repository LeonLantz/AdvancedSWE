package org.movie.manager.adapters;

import org.movie.manager.application.Services.CreditsService;
import org.movie.manager.application.Services.MetadataService;
import org.movie.manager.application.Services.MovieService;

public class Controller {
    private MovieService movieService;
    private CreditsService creditsService;
    private MetadataService metadataService;

    private IMDBapi imbdAPI;

    public Controller(MovieService movieService, CreditsService creditsService, MetadataService metadataService, IMDBapi imbdAPI) {
        this.movieService = movieService;
        this.creditsService = creditsService;
        this.metadataService = metadataService;
        this.imbdAPI = imbdAPI;
    }
}
