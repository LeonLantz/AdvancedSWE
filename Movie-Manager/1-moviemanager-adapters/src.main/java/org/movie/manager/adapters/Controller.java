package org.movie.manager.adapters;

import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.plugin.database.CSVDatabase;

import java.io.IOException;
import java.util.List;

public class Controller {

    private EntityFactory elementFactory;
    private String csv_path ;

    public Controller(String csv_path, EntityFactory elementFactory) {
        this.csv_path = csv_path;
        this.elementFactory = elementFactory;
    }

    public void loadCSVData() throws IOException {
        CSVDatabase csvDatabase;
        String filePath;
        List<String[]> csvData;

        //Credits
        filePath = csv_path + "Credits.csv";
        csvDatabase = new CSVDatabase( filePath );
        csvData = csvDatabase.readData();
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Credits.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Movie
        filePath = csv_path + "Movie.csv";
        csvDatabase = new CSVDatabase( filePath );
        csvData = csvDatabase.readData();
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Movie.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Metadata
        filePath = csv_path + "Metadata.csv";
        csvDatabase = new CSVDatabase( filePath );
        csvData = csvDatabase.readData();
        csvData.forEach( e -> {
            try {
                elementFactory.createElement(Metadata.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
