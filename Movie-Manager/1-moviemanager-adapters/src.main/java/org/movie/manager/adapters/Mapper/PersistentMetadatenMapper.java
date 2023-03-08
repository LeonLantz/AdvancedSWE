package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Metadaten.Metadata;

public class PersistentMetadatenMapper {
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
        String[] atts = new String[PersistentMetadatenMapper.CSVPositions.values().length];
        atts[PersistentMetadatenMapper.CSVPositions.METADATAID.ordinal()] = String.valueOf(medadataData.getMetadataID());
        atts[PersistentMetadatenMapper.CSVPositions.STATE.ordinal()] = medadataData.getAvailability().getState().toString();
        atts[PersistentMetadatenMapper.CSVPositions.NAMEORMEDIUM.ordinal()] = medadataData.getAvailability().getNameOrMedium();
        atts[PersistentMetadatenMapper.CSVPositions.DESCRIPTION.ordinal()] = medadataData.getAvailability().getDescription();
        atts[PersistentMetadatenMapper.CSVPositions.IMBDID.ordinal()] = medadataData.getImbDdata().getiMDBID();
        atts[PersistentMetadatenMapper.CSVPositions.IMDBRATING.ordinal()] = String.valueOf(medadataData.getImbDdata().getiMDBRating());
        atts[PersistentMetadatenMapper.CSVPositions.METASCORE.ordinal()] = String.valueOf(medadataData.getImbDdata().getMetascore());
        atts[PersistentMetadatenMapper.CSVPositions.RATING.ordinal()] = String.valueOf(medadataData.getOwnRating().getRating());
        atts[PersistentMetadatenMapper.CSVPositions.MOVIE.ordinal()] = String.valueOf(medadataData.getMovie().getMovieID());
        return atts;
    }
    public static String[] getCSVHeader() {
        return new String[]{PersistentMetadatenMapper.CSVPositions.METADATAID.name(), PersistentMetadatenMapper.CSVPositions.STATE.name(), PersistentMetadatenMapper.CSVPositions.NAMEORMEDIUM.name(),
                PersistentMetadatenMapper.CSVPositions.DESCRIPTION.name(), PersistentMetadatenMapper.CSVPositions.IMBDID.name(), PersistentMetadatenMapper.CSVPositions.IMDBRATING.name(),
                PersistentMetadatenMapper.CSVPositions.METASCORE.name(), PersistentMetadatenMapper.CSVPositions.RATING.name(), PersistentMetadatenMapper.CSVPositions.MOVIE.name()};
    }
}
