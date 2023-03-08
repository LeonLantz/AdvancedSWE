package org.movie.manager.adapters;

import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Movie.Movie;

import java.io.IOException;
import java.util.List;

public class Controller {

    private EntityFactory elementFactory;
    private CSVDatabase csvDatabase;

    public Controller(EntityFactory elementFactory, CSVDatabase csvDatabase) {
        this.elementFactory = elementFactory;
        this.csvDatabase = csvDatabase;
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
