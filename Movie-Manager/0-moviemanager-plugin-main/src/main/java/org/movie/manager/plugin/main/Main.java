package org.movie.manager.plugin.main;

import org.movie.manager.adapters.Controller;
import org.movie.manager.adapters.EntityFactory;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.application.Services.MovieService;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.plugin.csvdatabase.CSVDatabaseManager;
import org.movie.manager.plugin.imbd.OMDBapi;
import org.movie.manager.plugin.imbd.PropertyManager;

import java.io.IOException;
import java.util.UUID;


public class Main {

    public static void main(String[] args) throws IOException {
        String default_property_file_path= "0-moviemanager-plugin-main/target/classes/Conf/config.properties";
        String default_csv_folder_path= "0-moviemanager-plugin-main/target/classes/CSVFiles/";

        final String PROPERTY_FILE_PATH;
        final String CSV_FOLDER_PATH;

        //start movie manager
        System.out.println("Start movie manager");

        //init argument(s)
        if (args.length == 0){
            PROPERTY_FILE_PATH = default_property_file_path;
            CSV_FOLDER_PATH = default_csv_folder_path;
        } else if (args.length == 2 && args[0].equals("-c")){
            PROPERTY_FILE_PATH = default_property_file_path;
            CSV_FOLDER_PATH = args[1];
        } else if (args.length == 2 && args[0].equals("-p")){
            PROPERTY_FILE_PATH = args[1];
            CSV_FOLDER_PATH = default_csv_folder_path;
        } else if (args.length == 4 && args[0].equals("-c") && args[2].equals("-p")){
            CSV_FOLDER_PATH = args[1];
            PROPERTY_FILE_PATH = args[3];
        }else if (args.length == 4 && args[0].equals("-p") && args[2].equals("-c")){
            CSV_FOLDER_PATH = args[3];
            PROPERTY_FILE_PATH = args[1];
        }else {
            System.out.println("Please use: -c <csv_folder_path>");
            System.out.println("And/or    : -p <property_file_path>");
            throw new IllegalArgumentException("Wrong Argument(s)");
        }

        // Initialisation of an Entity Manager Factory
        GenericEntityManager entityManager = new GenericEntityManager();
        EntityFactory elementFactory = new EntityFactory( entityManager );

        // The Entity Manager Factory create a Entity Manager for MovieRepository: something like: MovieRepository movieRepository = new MovieRepository(elementFactory);
        //MovieRepository movieRepository = new MovieRepository(entityManager);

        // Creation of CSV DB
        CSVDatabaseManager csvDB = new CSVDatabaseManager(CSV_FOLDER_PATH);

        // Creation of Frontend-Design


        // Creation of an PropertyManager
        PropertyManager proMan;
        try {
            proMan = new PropertyManager(PROPERTY_FILE_PATH, null, null);
            proMan.printout(System.out, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Creation of IMBD-API which need a PropertyManger
        OMDBapi imbdAPI = new OMDBapi(proMan);

        // Creation of MovieService
        MovieService movieServie = new MovieService(null);

        // Initialisation and start of an Controller
        Controller controller = new Controller(elementFactory, csvDB);
        controller.loadCSVData();

        Movie movie = (Movie)entityManager.find(UUID.fromString("14a42dff-6c77-4ced-bf29-77e6068352ce"));
        System.out.println(movie.getTitel());

        System.out.println("Stop movie manager");
    }
}