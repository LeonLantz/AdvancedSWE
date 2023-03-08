package org.movie.manager.domain.Movie;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.IPersistable;
import org.movie.manager.domain.Metadaten.Metadata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.UUID;

public class Movie implements IPersistable {

    //A change should be possible, as typing errors can occur -> no final and for each variable getter and setter functions

    /*
    Issue ID:
    - Do IDs belong to the domain layer?
    - No: Has nothing to do with films per se, only with administration
    - Yes: Also interesting if you want to label your own media library. Then ID's also belong to the film. Of course also implement advantages, since it is easy to implement, can still be used later for other storage options. The idea of the program is simple use of the video library. CSV files offer the advantage of being able to be read without a program and by many other programs that support CSV.
    - Decision yes to ID in domain layer! Also for other classes, because of KISS and same structure principle
    */
    private final UUID movieID; //only getFunction()
    private String titel;
    private String genre;
    private int releaseYear;
    private int runningTimeInMin;


    private Metadata metadata;


    private Collection<Credits> directors = new ArrayList<>();;
    private Collection<Credits> actors = new ArrayList<>();;
    private Collection<Credits> screenwriters = new ArrayList<>();;

    public static enum CSVPositions {
        MOVIEID,
        TITEL,
        GENRE,
        RELEASEYEAR,
        METADATA,
        RUNNINGTIMEINMIN,
        DIRECTORS,
        ACTORS,
        SCREENWRITERS;

        private CSVPositions() {
        }
    }

    public Movie(UUID movieID, String titel, String genre, int releaseYear, int runningTimeInMin, Metadata metadata, Collection<Credits> directors, Collection<Credits> actors, Collection<Credits> screenwriters) {
        if(movieID != null)
            this.movieID = movieID;
        else
            this.movieID = UUID.randomUUID();

        this.titel = titel;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.runningTimeInMin = runningTimeInMin;
        this.metadata = metadata;
        if (directors != null) this.directors = directors;
        if (actors != null) this.actors = actors;
        if (screenwriters != null) this.screenwriters = screenwriters;
    }

    public UUID getMovieID() {
        return movieID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRunningTimeInMin() {
        return runningTimeInMin;
    }

    public void setRunningTimeInMin(int runningTimeInMin) {
        this.runningTimeInMin = runningTimeInMin;
    }

    public Collection<Credits> getDirectors() {
        return directors;
    }

    public void addDirectors(Credits director) {
        directors.add(director);
    }

    public Collection<Credits> getActors() {
        return actors;
    }

    public void addActor(Credits actor) {
        actors.add(actor);
    }

    public Collection<Credits> getScreenwriter() {
        return screenwriters;
    }

    public void addScreenwriter(Credits screenwriter) {
        screenwriters.add(screenwriter);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String[] getCSVData() {
        String[] atts = new String[Movie.CSVPositions.values().length];
        atts[Movie.CSVPositions.MOVIEID.ordinal()] = String.valueOf(this.getMovieID());
        atts[Movie.CSVPositions.TITEL.ordinal()] = this.getTitel();
        atts[Movie.CSVPositions.GENRE.ordinal()] = this.getGenre();
        atts[Movie.CSVPositions.RELEASEYEAR.ordinal()] = String.valueOf(this.getReleaseYear());
        atts[Movie.CSVPositions.RUNNINGTIMEINMIN.ordinal()] = String.valueOf(this.getRunningTimeInMin());

        if(this.directors.size() != 0) {
            StringJoiner sjDirector = new StringJoiner(",");
            this.directors.forEach((e) -> sjDirector.add(e.getCreditsID().toString()));
            atts[Movie.CSVPositions.DIRECTORS.ordinal()] = sjDirector.toString();
        } else {
            atts[Movie.CSVPositions.DIRECTORS.ordinal()] = " ";
        }

        if(this.actors.size() != 0) {
            StringJoiner sjActor = new StringJoiner(",");
            this.actors.forEach((e) -> sjActor.add(e.getCreditsID().toString()));
            atts[Movie.CSVPositions.ACTORS.ordinal()] = sjActor.toString();
        } else {
            atts[Movie.CSVPositions.ACTORS.ordinal()] = " ";
        }

        if(this.screenwriters.size() != 0) {
            StringJoiner sjScreenwriter = new StringJoiner(",");
            this.screenwriters.forEach((e) -> sjScreenwriter.add(e.getCreditsID().toString()));
            atts[Movie.CSVPositions.SCREENWRITERS.ordinal()] = sjScreenwriter.toString();
        } else {
            atts[Movie.CSVPositions.SCREENWRITERS.ordinal()] = " ";
        }
        return atts;
    }

    public static String[] getCSVHeader() {
        return new String[]{CSVPositions.MOVIEID.name(), CSVPositions.TITEL.name(), CSVPositions.GENRE.name(),
                CSVPositions.RELEASEYEAR.name(), CSVPositions.METADATA.name(), CSVPositions.RUNNINGTIMEINMIN.name(),
                CSVPositions.DIRECTORS.name(),CSVPositions.ACTORS.name(),CSVPositions.SCREENWRITERS.name()};
    }

    @Override
    public Object getPrimaryKey() {
        return movieID;
    }
}
