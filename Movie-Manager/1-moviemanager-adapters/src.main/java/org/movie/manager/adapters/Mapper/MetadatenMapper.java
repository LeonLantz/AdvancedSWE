package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Metadata.Metadata;

import java.util.Arrays;

public class MetadatenMapper {
    public static enum Header {
        METADATAID,
        STATE,
        NAMEORMEDIUM,
        DESCRIPTION,
        IMBDID,
        IMDBRATING,
        METASCORE,
        RATING,
        MOVIE;

        private Header() {
        }
    }

    public String[] mapData(Metadata medadataData) {
        String[] atts = new String[Header.values().length];
        atts[Header.METADATAID.ordinal()] = String.valueOf(medadataData.getMetadataID());
        atts[Header.STATE.ordinal()] = medadataData.getAvailability().getState().toString();
        atts[Header.NAMEORMEDIUM.ordinal()] = medadataData.getAvailability().getNameOrMedium();
        atts[Header.DESCRIPTION.ordinal()] = medadataData.getAvailability().getDescription();
        atts[Header.IMBDID.ordinal()] = medadataData.getImbDdata().getiMDBID();
        atts[Header.IMDBRATING.ordinal()] = String.valueOf(medadataData.getImbDdata().getiMDBRating());
        atts[Header.METASCORE.ordinal()] = String.valueOf(medadataData.getImbDdata().getMetascore());
        atts[Header.RATING.ordinal()] = String.valueOf(medadataData.getOwnRating().getRating());
        atts[Header.MOVIE.ordinal()] = String.valueOf(medadataData.getMovie().getMovieID());
        return atts;
    }
    public static String[] getHeader() {
        return Arrays.stream(Header.values()).map(Enum::name).toArray(String[]::new);
    }
}
