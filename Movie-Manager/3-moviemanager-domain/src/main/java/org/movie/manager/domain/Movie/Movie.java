package org.movie.manager.domain.Movie;
import org.movie.manager.domain.IPersistable;
import org.movie.manager.domain.Metadaten.Metadata;

import java.time.LocalDate;
import java.util.Collection;
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
    private LocalDate releaseYear;
    private int runningTimeInMin;


    private Metadata metadata;


    private Collection<Credits> directors;
    private Collection<Credits> actors;
    private Collection<Credits> screenwriters;

    public static enum CSVPositions {
        MOVIEID,
        TITEL,
        GENRE,
        RELEASEDATE,
        METADATA,
        RUNNINGTIMEINMIN,
        DIRECTORS,
        ACTORS,
        SCREENWRITERS;

        private CSVPositions() {
        }
    }

    public Movie(UUID movieID, String titel, String genre, LocalDate releaseYear, int runningTimeInMin, Metadata metadata, Collection<Credits> directors, Collection<Credits> actors, Collection<Credits> screenwriters) {
        this.movieID = movieID;
        this.titel = titel;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.runningTimeInMin = runningTimeInMin;
        this.metadata = metadata;
        this.directors = directors;
        this.actors = actors;
        this.screenwriters = screenwriters;
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

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
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

    @Override
    public Object getPrimaryKey() {
        return movieID;
    }
}
