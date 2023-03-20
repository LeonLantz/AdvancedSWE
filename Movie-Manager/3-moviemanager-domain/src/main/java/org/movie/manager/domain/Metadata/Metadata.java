package org.movie.manager.domain.Metadata;

import org.movie.manager.domain.Movie.MovieID;
import org.movie.manager.domain.Persistable;
import org.movie.manager.domain.Movie.Movie;

public class Metadata implements Persistable {

    private final MetadataID metadataID; //only getFunction()

    private Availability availability;

    private IMBDdata imbDdata;

    private Rating ownRating;

    private MovieID movie;

    public Metadata(MetadataID metadataID, Availability availability, IMBDdata imbDdata, Rating ownRating, MovieID movie) {
        if(metadataID != null)
            this.metadataID = metadataID;
        else
            this.metadataID = new MetadataID(null);

        this.availability = availability;
        this.imbDdata = imbDdata;
        this.ownRating = ownRating;
        this.movie = movie;
    }

    public MetadataID getMetadataID() {
        return metadataID;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public IMBDdata getImbDdata() {
        return imbDdata;
    }

    public void setImbDdata(IMBDdata imbDdata) {
        this.imbDdata = imbDdata;
    }

    public Rating getOwnRating() {
        return ownRating;
    }

    public void setOwnRating(Rating ownRating) {
        this.ownRating = ownRating;
    }

    public MovieID getMovie() {
        return movie;
    }

    public void setMovie(MovieID movie) {
        this.movie = movie;
    }

    @Override
    public Object getPrimaryKey() {
        return metadataID.getMetadataID();
    }
}
