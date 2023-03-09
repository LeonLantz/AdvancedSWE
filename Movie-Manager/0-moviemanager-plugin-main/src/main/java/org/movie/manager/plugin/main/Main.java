package org.movie.manager.plugin.main;

import org.movie.manager.adapters.Database;
import org.movie.manager.adapters.Controller;
import org.movie.manager.adapters.EntityFactory;
import org.movie.manager.adapters.IMDBapi;
import org.movie.manager.adapters.PersistentRepositories.PersistentFilmProfessionalRepository;
import org.movie.manager.adapters.PersistentRepositories.PersistentMetadataRepository;
import org.movie.manager.adapters.PersistentRepositories.PersistentMovieRepository;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.application.Services.FilmProfessionalService;
import org.movie.manager.application.Services.MetadataService;
import org.movie.manager.application.Services.MovieService;
import org.movie.manager.plugin.csvdatabase.CSVDatabaseManager;
import org.movie.manager.plugin.imbd.OMDBapi;
import org.movie.manager.plugin.imbd.PropertyManager;

public class Main {

    private static String PROPERTY_FILE_PATH= "0-moviemanager-plugin-main/target/classes/Conf/config.properties";
    private static String Data_FOLDER_PATH = "0-moviemanager-plugin-main/target/classes/CSVFiles/";

    public static void main(String[] args) {
        //start movie manager
        System.out.println("Start movie manager");

        //init argument(s)
        initArguments(args);

        // Creation of CSV-DB
        Database csvDB = new CSVDatabaseManager(Data_FOLDER_PATH);

        // Initialisation of an EntityManager and EntityManagerFactory
        GenericEntityManager entityManager = new GenericEntityManager();
        EntityFactory elementFactory = new EntityFactory(entityManager, csvDB);
        elementFactory.loadData();

        // Discuss: csvDB in each Repository or in entityManager?
        PersistentMovieRepository movieRepository = new PersistentMovieRepository(entityManager, csvDB);
        PersistentFilmProfessionalRepository persistentFilmProfessionalRepository = new PersistentFilmProfessionalRepository(entityManager, csvDB);
        PersistentMetadataRepository metadataRepository = new PersistentMetadataRepository(entityManager, csvDB);

        // Creation of an PropertyManager
        PropertyManager proMan;
        try {
            proMan = new PropertyManager(PROPERTY_FILE_PATH, null, null);
            proMan.printout(System.out, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Creation of IMBD-API
        IMDBapi imbdAPI = new OMDBapi(proMan);

        // Creation of Services
        MovieService movieServie = new MovieService(movieRepository);
        FilmProfessionalService filmProfessionalService = new FilmProfessionalService(persistentFilmProfessionalRepository);
        MetadataService metadataService = new MetadataService(metadataRepository);

        // Initialisation and start of an Controller
        Controller controller = new Controller(movieServie, filmProfessionalService, metadataService, imbdAPI);

        //stop movie manager
        System.out.println("Stop movie manager");
    }

    private static void initArguments(String[] args) {
        if (args.length == 2 && args[0].equals("-c")){
            Data_FOLDER_PATH = args[1];
        } else if (args.length == 2 && args[0].equals("-p")){
            PROPERTY_FILE_PATH = args[1];
        } else if (args.length == 4 && args[0].equals("-c") && args[2].equals("-p")){
            Data_FOLDER_PATH = args[1];
            PROPERTY_FILE_PATH = args[3];
        }else if (args.length == 4 && args[0].equals("-p") && args[2].equals("-c")){
            Data_FOLDER_PATH = args[3];
            PROPERTY_FILE_PATH = args[1];
        }else if (args.length != 0){
            System.out.println("Please use: -c <data_folder_path>");
            System.out.println("And/or    : -p <property_file_path>");
            throw new IllegalArgumentException("Wrong Argument(s)");
        }
    }
}