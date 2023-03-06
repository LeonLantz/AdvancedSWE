package org.movie.manager.plugin.main;

import org.movie.manager.application.MovieService;
import org.movie.manager.domain.MovieRepository;
import org.movie.manager.plugin.database.CSVDatabase;


public class Main {

    private static final String PROPERTY_DEFAULT_FILE = "./Conf/config.properties";
    private static final String CSV_PATH_DEFAULT = "./CSVFiles/";

    public static void main(String[] args) {
        System.out.println("Start movie manager");

        // Initialisation of an Entity Manager Factory
        // The Entity Manager Factory create a Entity Manager for MovieRepository: something like: MovieRepository movieRepository = new MovieRepository();

        CSVDatabase db = new CSVDatabase(CSV_PATH_DEFAULT);

        // Creation of Frontend-Design
        // Creation of an PropertyManager
        // Creation of IMBD-API which need a PropertyManger

        MovieService movieServie = new MovieService(null);

        // Initialisation and start of an Controller


        System.out.println("Stop movie manager");
    }
}