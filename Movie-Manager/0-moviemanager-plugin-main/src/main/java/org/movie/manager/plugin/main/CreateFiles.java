package org.movie.manager.plugin.main;

import org.movie.manager.adapters.Mapper.PersistentCreditsMapper;
import org.movie.manager.adapters.Mapper.PersistentMetadatenMapper;
import org.movie.manager.adapters.Mapper.PersistentMovieMapper;
import org.movie.manager.domain.Metadaten.*;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.plugin.database.CSVDatabase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CreateFiles {

    public static void main(String[] args) {
        createCSVFiles("0-moviemanager-plugin-main/target/classes/CSVFiles/");
    }

    public static void createCSVFiles(final String CSV_PATH_DEFAULT){

        try {
            Object[] dataLevel1;
            Object[][] objectArray2Dem;
            String filePath;
            CSVDatabase writer;

            File folder = new File(CSV_PATH_DEFAULT);
            if (!folder.mkdir())
                folder.createNewFile();

            //Credits
            Credits direktor1 = new Credits(null, "Sidney", "Lumet",null, null);
            Credits direktor2 = new Credits(null, "Christopher", "Nolan",null, null);
            Credits actor1_1 = new Credits(null, "Henry", "Fonda",null, null);
            Credits actor1_2 = new Credits(null, "Martin", "Balsam",null, null);

            Collection<Credits> direktor1List = new ArrayList<>();
            Collection<Credits> actor1List = new ArrayList<>();
            Collection<Credits> direktor2List = new ArrayList<>();
            direktor1List.add(direktor1);
            actor1List.add(actor1_1);
            actor1List.add(actor1_2);
            direktor2List.add(direktor2);


            //Movies
            Movie movie1 = new Movie(null, "12 Angry Men", "Drama", 1957, 96, null, direktor1List, actor1List, null);
            Movie movie2 = new Movie(null, "The Dark Knight", "Action,", 2008, 152, null, direktor2List, null, null);
            Movie movie3 = new Movie(null, "Fight Club", "Drama", 1999, 139, null, null, null, null);

            direktor1.addMovie(movie1);
            direktor2.addMovie(movie2);
            actor1_1.addMovie(movie1);
            actor1_2.addMovie(movie2);

            //Metadata
            Availability availability1 = new Availability(State.PHYSICALLY, "DVD", "");
            Availability availability2 = new Availability(State.ONLINE, "Amazon", "HD");
            Availability availability3 = new Availability(State.PHYSICALLY, "DVD", "");

            IMBDdata imbDdata1 = new IMBDdata("tt0050083", 9.0, 97);
            IMBDdata imbDdata2 = new IMBDdata("tt0468569", 9.0, 84);
            IMBDdata imbDdata3 = new IMBDdata("tt0137523", 8.8, 66);

            Rating rating9 = new Rating(9);
            Rating rating10 = new Rating(10);

            Metadata metadata1 = new Metadata(null, availability1, imbDdata1, rating10, movie1 );
            Metadata metadata2 = new Metadata(null, availability2, imbDdata2, rating9, movie2 );
            Metadata metadata3 = new Metadata(null, availability3, imbDdata3, rating9, movie3 );

            movie1.setMetadata(metadata1);
            movie2.setMetadata(metadata2);
            movie3.setMetadata(metadata3);

            filePath = CSV_PATH_DEFAULT + "Credits.csv"; // ohne "file:" am Anfang
            writer = new CSVDatabase(filePath);
            objectArray2Dem = new Object[4][];
            PersistentCreditsMapper persistentCreditsMapper = new PersistentCreditsMapper();
            objectArray2Dem[0]= persistentCreditsMapper.getCSVData(direktor1);
            objectArray2Dem[1]= persistentCreditsMapper.getCSVData(direktor2);
            objectArray2Dem[2]= persistentCreditsMapper.getCSVData(actor1_1);
            objectArray2Dem[3]= persistentCreditsMapper.getCSVData(actor1_2);
            writer.writeDataToFile(objectArray2Dem, PersistentCreditsMapper.getCSVHeader());

            filePath = CSV_PATH_DEFAULT + "Movie.csv"; // ohne "file:" am Anfang
            writer = new CSVDatabase(filePath);
            objectArray2Dem = new Object[3][];
            PersistentMovieMapper persistentMovieMapper = new PersistentMovieMapper();
            objectArray2Dem[0]= persistentMovieMapper.getCSVData(movie1);
            objectArray2Dem[1]= persistentMovieMapper.getCSVData(movie2);
            objectArray2Dem[2]= persistentMovieMapper.getCSVData(movie3);
            writer.writeDataToFile(objectArray2Dem, PersistentMovieMapper.getCSVHeader());

            filePath = CSV_PATH_DEFAULT + "Metadata.csv"; // ohne "file:" am Anfang
            writer = new CSVDatabase(filePath);
            objectArray2Dem = new Object[3][];
            PersistentMetadatenMapper persistentMetadatenMapper = new PersistentMetadatenMapper();
            objectArray2Dem[0]= persistentMetadatenMapper.getCSVData(metadata1);
            objectArray2Dem[1]= persistentMetadatenMapper.getCSVData(metadata2);
            objectArray2Dem[2]= persistentMetadatenMapper.getCSVData(metadata3);
            writer.writeDataToFile(objectArray2Dem, PersistentMetadatenMapper.getCSVHeader());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
