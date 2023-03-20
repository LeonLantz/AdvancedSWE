package org.movie.manager.domain.Metadata;

public class IMBDdata {

    private String iMDBID;
    /*
    Must be in its own aggregate, not part of a film.
    There can be a rating for the film, but it is not necessarily part of it.
    Especially if the film is new, this always has no rating.
    */

    private double iMDBRating;

    // METASCORE is a weighted average of reviews from top critics and publications for a given movie
    private int metascore;


    public IMBDdata(String iMDBID, double iMDBRating, int metascore) {
        this.iMDBID = iMDBID;
        this.iMDBRating = iMDBRating;
        this.metascore = metascore;
    }

    public String getiMDBID() {
        return iMDBID;
    }

    public void setiMDBID(String iMDBID) {
        this.iMDBID = iMDBID;
    }

    public double getiMDBRating() {
        return iMDBRating;
    }

    public void setiMDBRating(double iMDBRating) {
        this.iMDBRating = iMDBRating;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }
}
