package org.movie.manager.adapters;

import org.movie.manager.application.Services.Filter;
import org.movie.manager.application.Services.MovieEditService;
import org.movie.manager.application.Services.MovieFinderService;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.Rating;
import org.movie.manager.domain.Movie.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Controller {
    private MovieFinderService movieFinderService;
    private MovieEditService movieEditService;

    private IMDBapi imbdAPI;

    public Controller(MovieFinderService movieFinderService, MovieEditService movieEditService, IMDBapi imbdAPI) {
        this.movieFinderService = movieFinderService;
        this.movieEditService = movieEditService;
        this.imbdAPI = imbdAPI;

//        test();
    }

//    public void test(){
//        System.out.println("Test startet");
//        System.out.println(movieFinderService.getAllMovies());
//        Movie movie = movieFinderService.getMovie(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")).get();
//        List<Filter> filter;
//        Filter filterBig = new Filter("ownratingBigger", 9);
//        filter = new ArrayList<>();
//        filter.add(filterBig);
//        System.out.println(movieFinderService.getMoviesWithFilter(filter));
//        Filter filterSmall = new Filter("ownratingSmaller", 8);
//        filter = new ArrayList<>();
//        filter.add(filterSmall);
//        System.out.println(movieFinderService.getMoviesWithFilter(filter));
//        Filter filterOwn = new Filter("ownership", "ONLINE");
//        filter = new ArrayList<>();
//        filter.add(filterOwn);
//        System.out.println(movieFinderService.getMoviesWithFilter(filter));
//
//        Rating rating7 = new Rating(7);
//        Movie movieNew = new Movie(null, "Star Wars: Die letzten Jedi", "Action", 2017, 152, null, null, null, null);
//        Metadata metadata1 = new Metadata(null, null, null, rating7, movieNew.getMovieID() );
//        movieNew.setMetadataID(metadata1.getMetadataID());
//
//        movieEditService.saveNewMovie(movieNew, metadata1, null);
//        filter = new ArrayList<>();
//        filter.add(filterSmall);
//        System.out.println(movieFinderService.getMoviesWithFilter(filter));
//    }
}
