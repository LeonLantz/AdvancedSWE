package org.movie.manager.domain;

import java.util.Date;
import java.util.UUID;

public class Movie {

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
    private Date releaseDate;
    private int runningTimeInMin;
    private Credits director;
    private Credits actor;
    private Credits screenwriter;
    private Availability availability;

    /*
    Must be in its own aggregate, not part of a film.
    There can be a rating for the film, but it is not necessarily part of it.
    Especially if the film is new, this always has no rating.
    */
    private int iMDBID;
    private Double iMDBRating;
    private Double metascore;
    /*
    Own rating:
-   5-Star vs 10-Star Rating: 10-Star Rating more possible,
    more flexibility, 5-Star is basically multiplied by 2 is a 10-Star Rating,
    with IMBD your own rating is also a 10-Star Rating,
    everything speaks for you fixed 10-star rating

    Has to implemt as a value object!
     */
    private int ownRating;


    public Movie(String titel, String genre, Date releaseDate, UUID movieID, int iMDBID, int runningTimeInMin, int ownRating, Double iMDBRating, Double metascore, Credits director, Credits actor, Credits screenwriter, Availability availability) {
        this.titel = titel;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.movieID = movieID;
        this.iMDBID = iMDBID;
        this.runningTimeInMin = runningTimeInMin;
        this.ownRating = ownRating;
        this.iMDBRating = iMDBRating;
        this.metascore = metascore;
        this.director = director;
        this.actor = actor;
        this.screenwriter = screenwriter;
        this.availability = availability;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getiMDBID() {
        return iMDBID;
    }

    public void setiMDBID(int iMDBID) {
        this.iMDBID = iMDBID;
    }

    public int getRunningTimeInMin() {
        return runningTimeInMin;
    }

    public void setRunningTimeInMin(int runningTimeInMin) {
        this.runningTimeInMin = runningTimeInMin;
    }

    public int getOwnRating() {
        return ownRating;
    }

    public void setOwnRating(int ownRating) {
        this.ownRating = ownRating;
    }

    public Double getiMDBRating() {
        return iMDBRating;
    }

    public void setiMDBRating(Double iMDBRating) {
        this.iMDBRating = iMDBRating;
    }

    public Double getMetascore() {
        return metascore;
    }

    public void setMetascore(Double metascore) {
        this.metascore = metascore;
    }

    public Credits getDirector() {
        return director;
    }

    public void setDirector(Credits director) {
        this.director = director;
    }

    public Credits getActor() {
        return actor;
    }

    public void setActor(Credits actor) {
        this.actor = actor;
    }

    public Credits getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(Credits screenwriter) {
        this.screenwriter = screenwriter;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
