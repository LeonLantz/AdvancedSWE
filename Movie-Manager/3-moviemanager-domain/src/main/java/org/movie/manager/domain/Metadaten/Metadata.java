package org.movie.manager.domain.Metadaten;

import org.movie.manager.domain.IPersistable;
import org.movie.manager.domain.Movie.Movie;

import java.util.UUID;

public class Metadata implements IPersistable {

    private final UUID metadataID; //only getFunction()

    private Availability availability;

    private IMBDdata imbDdata;

    private Rating ownRating;

    private Movie movie;

    public static enum CSVPositions {
        METADATAID,
        STATE,
        NAMEORMEDIUM,
        DESCRIPTION,
        IMBDDATA,
        IMDBRATING,
        METASCORE,
        RATING,
        MOVIE;

        private CSVPositions() {
        }
    }

    public Metadata(UUID metadataID, Availability availability, IMBDdata imbDdata, Rating ownRating, Movie movie) {
        this.metadataID = metadataID;
        this.availability = availability;
        this.imbDdata = imbDdata;
        this.ownRating = ownRating;
        this.movie = movie;
    }

    public UUID getMetadataID() {
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

    @Override
    public Object getPrimaryKey() {
        return metadataID;
    }

    public Movie getMovie() {
        return movie;
    }
}
