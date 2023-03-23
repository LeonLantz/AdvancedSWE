package org.movie.manager.adapters;

import org.movie.manager.adapters.Events.*;
import org.movie.manager.application.Services.Filter;
import org.movie.manager.application.Services.MovieEditService;
import org.movie.manager.application.Services.MovieFinderService;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.Rating;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;
import org.movie.manager.domain.Persistable;

import java.util.*;

public class Controller implements IGUIEventListener, IUpdateEventSender {

    public enum Commands implements EventCommand {

        SET_MOVIES( "Controller.setMovies", Collection.class ),
        SET_DETAILDATA( "Controller.setDetailData", Collection.class );

        public final Class<?> payloadType;
        public final String cmdText;

        private Commands( String cmdText, Class<?> payloadType ) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return this.cmdText;
        }

        @Override
        public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }
    private MovieFinderService movieFinderService;
    private MovieEditService movieEditService;

    private IMDBapi imbdAPI;

    List<EventListener> allListeners = new ArrayList<>();

    public Controller(MovieFinderService movieFinderService, MovieEditService movieEditService, IMDBapi imbdAPI) {
        this.movieFinderService = movieFinderService;
        this.movieEditService = movieEditService;
        this.imbdAPI = imbdAPI;


//        test();
    }

    public void init() {
        fireUpdateEvent(new UpdateEvent(this, Commands.SET_MOVIES, movieFinderService.getAllMovies()));
    }

    @Override
    public void processGUIEvent(GUIEvent event) {
        if(event.getCmdText().equals("TableComponent.rowSelected")) {
            Movie selectedMovie = movieFinderService.getMovie(((MovieID)event.getData()).getMovieID()).get();
            ArrayList<Persistable> allMovieData = movieFinderService.getAllMovieData(selectedMovie.getMovieID().getMovieID(), selectedMovie.getMetadataID().getMetadataID());
            fireUpdateEvent(new UpdateEvent(this, Commands.SET_DETAILDATA, allMovieData));
        }
    }

    private void fireUpdateEvent( UpdateEvent ue ) {
        for (EventListener eventListener : allListeners) {
            if( eventListener instanceof IUpdateEventListener) {
                ((IUpdateEventListener)eventListener).processUpdateEvent(ue);
            }
        }
    }

    @Override
    public boolean addObserver(EventListener eL) {
        return this.allListeners.add(eL);
    }

    @Override
    public boolean removeObserver(EventListener eL) {
        return this.allListeners.remove(eL);
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
