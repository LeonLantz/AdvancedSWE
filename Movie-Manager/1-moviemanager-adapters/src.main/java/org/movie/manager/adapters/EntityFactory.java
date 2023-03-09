/*
Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
 */
package org.movie.manager.adapters;
import org.movie.manager.adapters.Mapper.FilmProfessionalsMapper;
import org.movie.manager.adapters.Mapper.MetadatenMapper;
import org.movie.manager.adapters.Mapper.MovieMapper;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Persistable;
import org.movie.manager.domain.Metadaten.*;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Movie.Movie;

import java.util.*;

public class EntityFactory { // for creating a family of objects

    private HashMap<String, String> mapOfUnreferencedElements;

    private GenericEntityManager entityManager;
    private Database csvDB;

    private Persistable persistableElement = null;

    public EntityFactory(GenericEntityManager em, Database csvDB) {
        this.entityManager = em;
        this.csvDB = csvDB;
        this.mapOfUnreferencedElements = new HashMap<>();
    }

    public void loadCSVData(){
        String filePath;
        List<String[]> csvData;

        //FilmProfessional
        filePath = "FilmProfessional.csv";
        csvData = csvDB.readData(filePath);
        csvData.forEach( e -> {
            try {
                this.createElement(FilmProfessional.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Movie
        filePath = "Movie.csv";
        csvData = csvDB.readData(filePath);
        csvData.forEach( e -> {
            try {
                this.createElement(Movie.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Metadata
        filePath = "Metadata.csv";
        csvData = csvDB.readData(filePath);
        csvData.forEach( e -> {
            try {
                this.createElement(Metadata.class, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        try { //resolve unresolved References
            resolveUnresolvedReferences();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param c the type of the class to be created
     * @param csvData the data read from a CSV file
     * @return the instance of the element just created
     * @throws Exception
     */
    public Persistable createElement(Class<?> c, String[] csvData) throws Exception { // 1. FILMPROFESSIONALID, 2. Movies, 3. Metadate

        if (c == null) {
            throw new IllegalArgumentException("class must not be null");
        } else if( c == FilmProfessional.class ) {
            UUID filmProfessionalID = UUID.fromString(csvData[FilmProfessionalsMapper.Header.FILMPROFESSIONALID.ordinal()]);
            String firstName = csvData[FilmProfessionalsMapper.Header.FIRSTNAME.ordinal()];
            String secondName = csvData[FilmProfessionalsMapper.Header.SECONDNAME.ordinal()];
            String biography = csvData[FilmProfessionalsMapper.Header.BIOGRAPHY.ordinal()];

            String listOfMoviesString = csvData[FilmProfessionalsMapper.Header.MOVIES.ordinal()];
            List<Movie> listMovies = new ArrayList<Movie>();;
            try {
                List<Persistable> realRefsTemp = this.getReferences(Movie.class, listOfMoviesString);
                for (Persistable iP : realRefsTemp) {
                    listMovies.add((Movie) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(filmProfessionalID.toString(), listOfMoviesString);
            }


            persistableElement = new FilmProfessional(filmProfessionalID, firstName, secondName, biography, listMovies);
        } else if( c == Movie.class ) {
            UUID movieID = UUID.fromString(csvData[MovieMapper.Header.MOVIEID.ordinal()]);
            String titel = csvData[MovieMapper.Header.TITEL.ordinal()];
            String genre = csvData[MovieMapper.Header.GENRE.ordinal()];
            int releaseYear = Integer.parseInt(csvData[MovieMapper.Header.RELEASEYEAR.ordinal()]);

            int runningTimeInMin = Integer.parseInt(csvData[MovieMapper.Header.RUNNINGTIMEINMIN.ordinal()]);


            String metadatenString = csvData[MovieMapper.Header.METADATA.ordinal()];
            Metadata metadaten = null;
            try {
                metadaten = ((Metadata) this.getReferences(Metadata.class, metadatenString));
            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), metadatenString);
            }


            String listOfDirectorsString = csvData[MovieMapper.Header.DIRECTORS.ordinal()];
            List<FilmProfessional> listDirectors = new ArrayList<FilmProfessional>();
            try {
                List<Persistable> realRefsTemp = this.getReferences(FilmProfessional.class, listOfDirectorsString);
                for (Persistable iP : realRefsTemp) {
                    listDirectors.add((FilmProfessional) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfDirectorsString);
            }

            String listOfActorsString = csvData[MovieMapper.Header.ACTORS.ordinal()];
            List<FilmProfessional> listActors = new ArrayList<FilmProfessional>();;
            try {
                List<Persistable> realRefsTemp = this.getReferences(FilmProfessional.class, listOfActorsString);
                for (Persistable iP : realRefsTemp) {
                    listActors.add((FilmProfessional) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfActorsString);
            }

            String listOfScreenwritersString = csvData[MovieMapper.Header.SCREENWRITERS.ordinal()];
            List<FilmProfessional> listScreenwriters = new ArrayList<FilmProfessional>();;
            try {
                List<Persistable> realRefsTemp = this.getReferences(FilmProfessional.class, listOfScreenwritersString);
                for (Persistable iP : realRefsTemp) {
                    listScreenwriters.add((FilmProfessional) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfScreenwritersString);
            }

            persistableElement = new Movie(movieID, titel, genre, releaseYear, runningTimeInMin, metadaten, listDirectors, listActors, listScreenwriters);


        } else if( c == Metadata.class ) {
            //movieID
            UUID metaDataID = UUID.fromString(csvData[MetadatenMapper.Header.METADATAID.ordinal()]);

            //availability
            State state = State.valueOf(csvData[MetadatenMapper.Header.STATE.ordinal()]);
            String nameOrMedium = csvData[MetadatenMapper.Header.NAMEORMEDIUM.ordinal()];
            String description = csvData[MetadatenMapper.Header.DESCRIPTION.ordinal()];
            Availability availability = new Availability(state, nameOrMedium, description);

            //imbDdata
            String iMDBID = csvData[MetadatenMapper.Header.IMBDID.ordinal()];
            double iMDBRating = Double.parseDouble(csvData[MetadatenMapper.Header.IMDBRATING.ordinal()]);
            int metascore = Integer.parseInt(csvData[MetadatenMapper.Header.METASCORE.ordinal()]);
            IMBDdata imbDdata = new IMBDdata(iMDBID, iMDBRating, metascore );

            //ownRating
            int rating = Integer.parseInt(csvData[MetadatenMapper.Header.RATING.ordinal()]);
            Rating ownRating = new Rating(rating);

            //movie
            String movieString = csvData[MetadatenMapper.Header.MOVIE.ordinal()];
            Movie movie = null;
            try {
                movie = ((Movie) this.getReferences(Movie.class, movieString).get(0));
            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(metaDataID.toString(), movieString);
            }

            persistableElement = new Metadata(metaDataID, availability, imbDdata, ownRating, movie);
        }

        entityManager.persist( persistableElement );

        return persistableElement;
    }
    /**
     *
     * @param c
     * @param stringIDs
     * @return
     * @throws Exception
     */
    private List<Persistable> getReferences(Class<?> c, String stringIDs) throws Exception {
        List<Persistable> refs = new ArrayList<>();
        if( stringIDs == null  ||  stringIDs.isEmpty() ) throw new RuntimeException( "List of refs is empty or null" );

        String[] arrIDs = stringIDs.split(",");

        for( String sId: arrIDs ){
            if( !sId.isEmpty() && !(sId.indexOf( ' ' ) >= 0) ){
                UUID SUUID = UUID.fromString(sId);

                Persistable ae = entityManager.find(c, SUUID );
                if( ae != null )
                    refs.add( ae );
                else
                    throw new IllegalStateException();
            }
        }

        return refs;
    }

    /**
     * for compatibility reasons, this method simply delegates to {@link #resolveUnreferencedReferences()}
     * @throws Exception
     */
    public void resolveUnresolvedReferences() throws Exception {
        this.resolveUnreferencedReferences();
    }

    /**
     * resolve the unresolved references stored in the local HashMap
     * @throws Exception
     */
    public void resolveUnreferencedReferences() throws Exception {
        for( String key : this.mapOfUnreferencedElements.keySet() ){
            UUID keyUUID = UUID.fromString(key);
            Persistable ip = this.entityManager.find( keyUUID );
            String refs = this.mapOfUnreferencedElements.get( key );

            if( ip instanceof Movie) { // Movies should be initialized after FILMPROFESSIONALID
                List<Persistable> refList = getReferences(Metadata.class, refs);
                refList.forEach( e -> ((Movie)ip).setMetadata( (Metadata)e ) );
            }else if( ip instanceof FilmProfessional) { // Movies should be initialized after FILMPROFESSIONALID
                List<Persistable> refList = getReferences(Movie.class, refs);
                refList.forEach( e -> ((FilmProfessional)ip).addMovie( (Movie)e ) );
            }
        }
    }

}
