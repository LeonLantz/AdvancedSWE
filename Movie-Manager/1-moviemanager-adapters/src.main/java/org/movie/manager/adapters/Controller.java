package org.movie.manager.adapters;

import org.movie.manager.application.Services.MovieEditService;
import org.movie.manager.application.Services.MovieFinderService;

public class Controller {
    private MovieFinderService movieFinderService;
    private MovieEditService movieEditService;

    private IMDBapi imbdAPI;

    public Controller(MovieFinderService movieFinderService, MovieEditService movieEditService, IMDBapi imbdAPI) {
        this.movieFinderService = movieFinderService;
        this.imbdAPI = imbdAPI;
    }
}
