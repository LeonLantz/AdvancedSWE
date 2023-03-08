package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Metadaten.Metadata;

public class CSVMetadatenMapper {
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

    public String[] getCSVData(Metadata medadataData) {
        String[] atts = new String[CSVMetadatenMapper.CSVPositions.values().length];
        atts[CSVMetadatenMapper.CSVPositions.METADATAID.ordinal()] = String.valueOf(medadataData.getMetadataID());
        atts[CSVMetadatenMapper.CSVPositions.STATE.ordinal()] = medadataData.getAvailability().getState().toString();
        atts[CSVMetadatenMapper.CSVPositions.NAMEORMEDIUM.ordinal()] = medadataData.getAvailability().getNameOrMedium();
        atts[CSVMetadatenMapper.CSVPositions.DESCRIPTION.ordinal()] = medadataData.getAvailability().getDescription();
        atts[CSVMetadatenMapper.CSVPositions.IMBDID.ordinal()] = medadataData.getImbDdata().getiMDBID();
        atts[CSVMetadatenMapper.CSVPositions.IMDBRATING.ordinal()] = String.valueOf(medadataData.getImbDdata().getiMDBRating());
        atts[CSVMetadatenMapper.CSVPositions.METASCORE.ordinal()] = String.valueOf(medadataData.getImbDdata().getMetascore());
        atts[CSVMetadatenMapper.CSVPositions.RATING.ordinal()] = String.valueOf(medadataData.getOwnRating().getRating());
        atts[CSVMetadatenMapper.CSVPositions.MOVIE.ordinal()] = String.valueOf(medadataData.getMovie().getMovieID());
        return atts;
    }
    public static String[] getCSVHeader() {
        return new String[]{CSVMetadatenMapper.CSVPositions.METADATAID.name(), CSVMetadatenMapper.CSVPositions.STATE.name(), CSVMetadatenMapper.CSVPositions.NAMEORMEDIUM.name(),
                CSVMetadatenMapper.CSVPositions.DESCRIPTION.name(), CSVMetadatenMapper.CSVPositions.IMBDID.name(), CSVMetadatenMapper.CSVPositions.IMDBRATING.name(),
                CSVMetadatenMapper.CSVPositions.METASCORE.name(), CSVMetadatenMapper.CSVPositions.RATING.name(), CSVMetadatenMapper.CSVPositions.MOVIE.name()};
    }
}
