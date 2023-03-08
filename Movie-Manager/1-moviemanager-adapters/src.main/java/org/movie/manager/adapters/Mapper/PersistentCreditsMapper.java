package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Credits.Credits;

import java.util.StringJoiner;

public class PersistentCreditsMapper {

    public static enum CSVPositions {
        CREDITSID,
        FIRSTNAME,
        SECONDNAME,
        BIOGRAPHY,

        MOVIES;

        private CSVPositions() {
        }
    }

    public String[] getCSVData(Credits creditsData) {
        String[] atts = new String[PersistentCreditsMapper.CSVPositions.values().length];
        atts[PersistentCreditsMapper.CSVPositions.CREDITSID.ordinal()] = String.valueOf(creditsData.getCreditsID());
        atts[PersistentCreditsMapper.CSVPositions.FIRSTNAME.ordinal()] = creditsData.getFirstName();
        atts[PersistentCreditsMapper.CSVPositions.SECONDNAME.ordinal()] = creditsData.getSecondName();
        atts[PersistentCreditsMapper.CSVPositions.BIOGRAPHY.ordinal()] = creditsData.getBiography();

        if(creditsData.getMovies().size() != 0) {
            StringJoiner sjMovie = new StringJoiner(",");
            creditsData.getMovies().forEach((e) -> sjMovie.add(e.getMovieID().toString()));
            atts[PersistentCreditsMapper.CSVPositions.MOVIES.ordinal()] = sjMovie.toString();
        } else {
            atts[PersistentCreditsMapper.CSVPositions.MOVIES.ordinal()] = " ";
        }
        return atts;
    }

    public static String[] getCSVHeader() {
        return new String[]{PersistentCreditsMapper.CSVPositions.CREDITSID.name(), PersistentCreditsMapper.CSVPositions.FIRSTNAME.name(),
                PersistentCreditsMapper.CSVPositions.SECONDNAME.name(), PersistentCreditsMapper.CSVPositions.BIOGRAPHY.name()};
    }
}
