package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Movie.Movie;

import java.util.StringJoiner;

public class CSVMovieMapper {

    public static enum CSVPositions {
        MOVIEID,
        TITEL,
        GENRE,
        RELEASEYEAR,
        METADATA,
        RUNNINGTIMEINMIN,
        DIRECTORS,
        ACTORS,
        SCREENWRITERS;

        private CSVPositions() {
        }
    }
    public String[] getCSVData(Movie movieData) {
        String[] atts = new String[CSVMovieMapper.CSVPositions.values().length];
        atts[CSVMovieMapper.CSVPositions.MOVIEID.ordinal()] = String.valueOf(movieData.getMovieID());
        atts[CSVMovieMapper.CSVPositions.TITEL.ordinal()] = movieData.getTitel();
        atts[CSVMovieMapper.CSVPositions.GENRE.ordinal()] = movieData.getGenre();
        atts[CSVMovieMapper.CSVPositions.RELEASEYEAR.ordinal()] = String.valueOf(movieData.getReleaseYear());
        atts[CSVMovieMapper.CSVPositions.RUNNINGTIMEINMIN.ordinal()] = String.valueOf(movieData.getRunningTimeInMin());

        if(movieData.getDirectors().size() != 0) {
            StringJoiner sjDirector = new StringJoiner(",");
            movieData.getDirectors().forEach((e) -> sjDirector.add(e.getCreditsID().toString()));
            atts[CSVMovieMapper.CSVPositions.DIRECTORS.ordinal()] = sjDirector.toString();
        } else {
            atts[CSVMovieMapper.CSVPositions.DIRECTORS.ordinal()] = " ";
        }

        if(movieData.getActors().size() != 0) {
            StringJoiner sjActor = new StringJoiner(",");
            movieData.getActors().forEach((e) -> sjActor.add(e.getCreditsID().toString()));
            atts[CSVMovieMapper.CSVPositions.ACTORS.ordinal()] = sjActor.toString();
        } else {
            atts[CSVMovieMapper.CSVPositions.ACTORS.ordinal()] = " ";
        }

        if(movieData.getScreenwriter().size() != 0) {
            StringJoiner sjScreenwriter = new StringJoiner(",");
            movieData.getScreenwriter().forEach((e) -> sjScreenwriter.add(e.getCreditsID().toString()));
            atts[CSVMovieMapper.CSVPositions.SCREENWRITERS.ordinal()] = sjScreenwriter.toString();
        } else {
            atts[CSVMovieMapper.CSVPositions.SCREENWRITERS.ordinal()] = " ";
        }
        return atts;
    }

    public static String[] getCSVHeader() {
        return new String[]{CSVMovieMapper.CSVPositions.MOVIEID.name(), CSVMovieMapper.CSVPositions.TITEL.name(), CSVMovieMapper.CSVPositions.GENRE.name(),
                CSVMovieMapper.CSVPositions.RELEASEYEAR.name(), CSVMovieMapper.CSVPositions.METADATA.name(), CSVMovieMapper.CSVPositions.RUNNINGTIMEINMIN.name(),
                CSVMovieMapper.CSVPositions.DIRECTORS.name(), CSVMovieMapper.CSVPositions.ACTORS.name(), CSVMovieMapper.CSVPositions.SCREENWRITERS.name()};
    }
}
