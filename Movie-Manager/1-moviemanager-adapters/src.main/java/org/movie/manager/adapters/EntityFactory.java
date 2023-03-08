/*
Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
 */
package org.movie.manager.adapters;
import org.movie.manager.adapters.Mapper.PersistentCreditsMapper;
import org.movie.manager.adapters.Mapper.PersistentMetadatenMapper;
import org.movie.manager.adapters.Mapper.PersistentMovieMapper;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Persistable;
import org.movie.manager.domain.Metadaten.*;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Movie.Movie;

import java.util.*;

public class EntityFactory {

    private HashMap<String, String> mapOfUnreferencedElements = new HashMap<>();

    private GenericEntityManager entityManager = null;

    private Persistable persistableElement = null;

    public EntityFactory(GenericEntityManager em) {
        this.entityManager = em;
    }

    /**
     *
     * @param c the type of the class to be created
     * @param csvData the data read from a CSV file
     * @return the instance of the element just created
     * @throws Exception
     */
    public Persistable createElement(Class<?> c, String[] csvData) throws Exception { // 1. Credits, 2. Movies, 3. Metadate

        if (c == null) {
            throw new IllegalArgumentException("class must not be null");
        } else if( c == Credits.class ) {
            UUID CreditsID = UUID.fromString(csvData[PersistentCreditsMapper.CSVPositions.CREDITSID.ordinal()]);
            String firstName = csvData[PersistentCreditsMapper.CSVPositions.FIRSTNAME.ordinal()];
            String secondName = csvData[PersistentCreditsMapper.CSVPositions.SECONDNAME.ordinal()];
            String biography = csvData[PersistentCreditsMapper.CSVPositions.BIOGRAPHY.ordinal()];

            String listOfMoviesString = csvData[PersistentCreditsMapper.CSVPositions.MOVIES.ordinal()];
            List<Movie> listMovies = null;
            try {
                List<Persistable> realRefsTemp = this.getReferences(Credits.class, listOfMoviesString);
                for (Persistable iP : realRefsTemp) {
                    listMovies.add((Movie) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(CreditsID.toString(), listOfMoviesString);
            }


            persistableElement = new Credits(CreditsID, firstName, secondName, biography, listMovies);
        } else if( c == Movie.class ) {
            UUID movieID = UUID.fromString(csvData[PersistentMovieMapper.CSVPositions.MOVIEID.ordinal()]);
            String titel = csvData[PersistentMovieMapper.CSVPositions.TITEL.ordinal()];
            String genre = csvData[PersistentMovieMapper.CSVPositions.GENRE.ordinal()];
            int releaseYear = Integer.parseInt(csvData[PersistentMovieMapper.CSVPositions.RELEASEYEAR.ordinal()]);

            int runningTimeInMin = Integer.parseInt(csvData[PersistentMovieMapper.CSVPositions.RUNNINGTIMEINMIN.ordinal()]);


            String metadatenString = csvData[PersistentMovieMapper.CSVPositions.METADATA.ordinal()];
            Metadata metadaten = null;
            try {
                metadaten = ((Metadata) this.getReferences(Metadata.class, metadatenString).get(0));
            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), metadatenString);
            }


            String listOfDirectorsString = csvData[PersistentMovieMapper.CSVPositions.DIRECTORS.ordinal()];
            List<Credits> listDirectors = null;
            try {
                List<Persistable> realRefsTemp = this.getReferences(Credits.class, listOfDirectorsString);
                for (Persistable iP : realRefsTemp) {
                    listDirectors.add((Credits) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfDirectorsString);
            }

            String listOfActorsString = csvData[PersistentMovieMapper.CSVPositions.ACTORS.ordinal()];
            List<Credits> listActors = null;
            try {
                List<Persistable> realRefsTemp = this.getReferences(Credits.class, listOfActorsString);
                for (Persistable iP : realRefsTemp) {
                    listActors.add((Credits) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfActorsString);
            }

            String listOfScreenwritersString = csvData[PersistentMovieMapper.CSVPositions.SCREENWRITERS.ordinal()];
            List<Credits> listScreenwriters = null;
            try {
                List<Persistable> realRefsTemp = this.getReferences(Credits.class, listOfScreenwritersString);
                for (Persistable iP : realRefsTemp) {
                    listScreenwriters.add((Credits) iP);
                }

            } catch (Exception var13) {
                this.mapOfUnreferencedElements.put(movieID.toString(), listOfScreenwritersString);
            }

            persistableElement = new Movie(movieID, titel, genre, releaseYear, runningTimeInMin, metadaten, listDirectors, listActors, listScreenwriters);


        } else if( c == Metadata.class ) {
            //movieID
            UUID metaDataID = UUID.fromString(csvData[PersistentMetadatenMapper.CSVPositions.METADATAID.ordinal()]);

            //availability
            State state = State.valueOf(csvData[PersistentMetadatenMapper.CSVPositions.STATE.ordinal()]);
            String nameOrMedium = csvData[PersistentMetadatenMapper.CSVPositions.NAMEORMEDIUM.ordinal()];
            String description = csvData[PersistentMetadatenMapper.CSVPositions.DESCRIPTION.ordinal()];
            Availability availability = new Availability(state, nameOrMedium, description);

            //imbDdata
            String iMDBID = csvData[PersistentMetadatenMapper.CSVPositions.IMBDID.ordinal()];
            double iMDBRating = Double.parseDouble(csvData[PersistentMetadatenMapper.CSVPositions.IMDBRATING.ordinal()]);
            int metascore = Integer.parseInt(csvData[PersistentMetadatenMapper.CSVPositions.METASCORE.ordinal()]);
            IMBDdata imbDdata = new IMBDdata(iMDBID, iMDBRating, metascore );

            //ownRating
            int rating = Integer.parseInt(csvData[PersistentMetadatenMapper.CSVPositions.RATING.ordinal()]);
            Rating ownRating = new Rating(rating);

            //movie
            String movieString = csvData[PersistentMetadatenMapper.CSVPositions.MOVIE.ordinal()];
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
                Persistable ae = entityManager.find(c, "getPrimaryKey", sId );
                if( ae != null )
                    refs.add( ae );
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
            Persistable ip = this.entityManager.find( key );
            String refs = this.mapOfUnreferencedElements.get( key );

            if( ip instanceof Credits ) { // Movies should be initialized after Credits
                List<Persistable> refList = getReferences(Movie.class, refs);
                refList.forEach( e -> ((Credits)ip).addMovie( (Movie)e ) );
            }
        }
    }

}
