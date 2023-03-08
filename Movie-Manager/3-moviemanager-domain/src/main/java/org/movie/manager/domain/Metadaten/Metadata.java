package org.movie.manager.domain.Metadaten;

import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.IPersistable;
import org.movie.manager.domain.Movie.Movie;

import java.util.StringJoiner;
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
        IMBDID,
        IMDBRATING,
        METASCORE,
        RATING,
        MOVIE;

        private CSVPositions() {
        }
    }

    public Metadata(UUID metadataID, Availability availability, IMBDdata imbDdata, Rating ownRating, Movie movie) {
        if(metadataID != null)
            this.metadataID = metadataID;
        else
            this.metadataID = UUID.randomUUID();

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

    public String[] getCSVData() {
        String[] atts = new String[Metadata.CSVPositions.values().length];
        atts[Metadata.CSVPositions.METADATAID.ordinal()] = String.valueOf(this.getMetadataID());
        atts[Metadata.CSVPositions.STATE.ordinal()] = this.getAvailability().getState().toString();
        atts[Metadata.CSVPositions.NAMEORMEDIUM.ordinal()] = this.getAvailability().getNameOrMedium();
        atts[Metadata.CSVPositions.DESCRIPTION.ordinal()] = this.getAvailability().getDescription();
        atts[Metadata.CSVPositions.IMBDID.ordinal()] = this.getImbDdata().getiMDBID();
        atts[Metadata.CSVPositions.IMDBRATING.ordinal()] = String.valueOf(this.getImbDdata().getiMDBRating());
        atts[Metadata.CSVPositions.METASCORE.ordinal()] = String.valueOf(this.getImbDdata().getMetascore());
        atts[Metadata.CSVPositions.RATING.ordinal()] = String.valueOf(this.getOwnRating().getRating());
        atts[Metadata.CSVPositions.MOVIE.ordinal()] = String.valueOf(this.getMovie().getMovieID());
        return atts;
    }
    public static String[] getCSVHeader() {
        return new String[]{Metadata.CSVPositions.METADATAID.name(), Metadata.CSVPositions.STATE.name(), Metadata.CSVPositions.NAMEORMEDIUM.name(),
                Metadata.CSVPositions.DESCRIPTION.name(), Metadata.CSVPositions.IMBDID.name(), Metadata.CSVPositions.IMDBRATING.name(),
                Metadata.CSVPositions.METASCORE.name(), Metadata.CSVPositions.RATING.name(), Metadata.CSVPositions.MOVIE.name()};
    }

    public Movie getMovie() {
        return movie;
    }
}
