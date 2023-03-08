package org.movie.manager.adapters;

import org.movie.manager.application.Services.CreditsService;
import org.movie.manager.application.Services.MetadataService;
import org.movie.manager.application.Services.MovieService;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Movie.Movie;

import java.util.List;

public class Controller {

    private EntityFactory elementFactory;
    private CSVDatabase csvDatabase;
    private MovieService movieService;
    private CreditsService creditsService;
    private MetadataService metadataService;

    private IMDBapi imbdAPI;

    public Controller(EntityFactory elementFactory, CSVDatabase csvDatabase, MovieService movieService, CreditsService creditsService, MetadataService metadataService, IMDBapi imbdAPI) {
        this.elementFactory = elementFactory;
        this.csvDatabase = csvDatabase;
        this.movieService = movieService;
        this.creditsService = creditsService;
        this.metadataService = metadataService;
        this.imbdAPI = imbdAPI;
    }

    public void loadCSVData(){
        String filePath;
        List<String[]> csvData;

        //Credits
        filePath = "Credits.csv";
        csvData = csvDatabase.readData(filePath);
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Credits.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Movie
        filePath = "Movie.csv";
        csvData = csvDatabase.readData(filePath);
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Movie.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Metadata
        filePath = "Metadata.csv";
        csvData = csvDatabase.readData(filePath);
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Metadata.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
