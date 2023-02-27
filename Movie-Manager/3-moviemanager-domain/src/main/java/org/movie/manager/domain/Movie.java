package org.movie.manager.domain;

import java.util.Date;

public class Movie {

    private String titel;
    private String genre;
    private Date releaseDate;
    private int iMDBID;
    private int runningTimeInMin;
    private int ownRating;
    private Double iMDBRating;
    private Double metascore;

    private Credits director;
    private Credits actor;
    private Credits screenwriter;

    private Availability availability;

    public Movie(String titel, String genre, Date releaseDate, int iMDBID, int runningTimeInMin, int ownRating, Double iMDBRating, Double metascore, Credits director, Credits actor, Credits screenwriter, Availability availability) {
        this.titel = titel;
        this.genre = genre;
        this.releaseDate = releaseDate;
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
