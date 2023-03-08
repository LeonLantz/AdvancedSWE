package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Credits.Credits;

import java.util.StringJoiner;

public class CSVCreditsMapper {

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
        String[] atts = new String[CSVCreditsMapper.CSVPositions.values().length];
        atts[CSVCreditsMapper.CSVPositions.CREDITSID.ordinal()] = String.valueOf(creditsData.getCreditsID());
        atts[CSVCreditsMapper.CSVPositions.FIRSTNAME.ordinal()] = creditsData.getFirstName();
        atts[CSVCreditsMapper.CSVPositions.SECONDNAME.ordinal()] = creditsData.getSecondName();
        atts[CSVCreditsMapper.CSVPositions.BIOGRAPHY.ordinal()] = creditsData.getBiography();

        if(creditsData.getMovies().size() != 0) {
            StringJoiner sjMovie = new StringJoiner(",");
            creditsData.getMovies().forEach((e) -> sjMovie.add(e.getMovieID().toString()));
            atts[CSVCreditsMapper.CSVPositions.MOVIES.ordinal()] = sjMovie.toString();
        } else {
            atts[CSVCreditsMapper.CSVPositions.MOVIES.ordinal()] = " ";
        }
        return atts;
    }

    public static String[] getCSVHeader() {
        return new String[]{CSVCreditsMapper.CSVPositions.CREDITSID.name(), CSVCreditsMapper.CSVPositions.FIRSTNAME.name(),
                CSVCreditsMapper.CSVPositions.SECONDNAME.name(), CSVCreditsMapper.CSVPositions.BIOGRAPHY.name()};
    }
}
