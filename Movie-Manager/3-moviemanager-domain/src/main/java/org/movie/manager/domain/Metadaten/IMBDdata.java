package org.movie.manager.domain.Metadaten;

public class IMBDdata {

    private int iMDBID;
    /*
    Must be in its own aggregate, not part of a film.
    There can be a rating for the film, but it is not necessarily part of it.
    Especially if the film is new, this always has no rating.
    */

    private double iMDBRating;

    // METASCORE is a weighted average of reviews from top critics and publications for a given movie
    private double metascore;


    public IMBDdata(int iMDBID, double iMDBRating, double metascore) {
        this.iMDBID = iMDBID;
        this.iMDBRating = iMDBRating;
        this.metascore = metascore;
    }

    public int getiMDBID() {
        return iMDBID;
    }

    public void setiMDBID(int iMDBID) {
        this.iMDBID = iMDBID;
    }

    public double getiMDBRating() {
        return iMDBRating;
    }

    public void setiMDBRating(double iMDBRating) {
        this.iMDBRating = iMDBRating;
    }

    public double getMetascore() {
        return metascore;
    }

    public void setMetascore(double metascore) {
        this.metascore = metascore;
    }
}
