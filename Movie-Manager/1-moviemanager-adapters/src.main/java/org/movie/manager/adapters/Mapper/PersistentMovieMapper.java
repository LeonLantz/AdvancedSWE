package org.movie.manager.adapters.Mapper;

import org.movie.manager.domain.Movie.Movie;

import java.util.StringJoiner;

public class PersistentMovieMapper {

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
        String[] atts = new String[PersistentMovieMapper.CSVPositions.values().length];
        atts[PersistentMovieMapper.CSVPositions.MOVIEID.ordinal()] = String.valueOf(movieData.getMovieID());
        atts[PersistentMovieMapper.CSVPositions.TITEL.ordinal()] = movieData.getTitel();
        atts[PersistentMovieMapper.CSVPositions.GENRE.ordinal()] = movieData.getGenre();
        atts[PersistentMovieMapper.CSVPositions.RELEASEYEAR.ordinal()] = String.valueOf(movieData.getReleaseYear());
        atts[PersistentMovieMapper.CSVPositions.RUNNINGTIMEINMIN.ordinal()] = String.valueOf(movieData.getRunningTimeInMin());

        if(movieData.getDirectors().size() != 0) {
            StringJoiner sjDirector = new StringJoiner(",");
            movieData.getDirectors().forEach((e) -> sjDirector.add(e.getCreditsID().toString()));
            atts[PersistentMovieMapper.CSVPositions.DIRECTORS.ordinal()] = sjDirector.toString();
        } else {
            atts[PersistentMovieMapper.CSVPositions.DIRECTORS.ordinal()] = " ";
        }

        if(movieData.getActors().size() != 0) {
            StringJoiner sjActor = new StringJoiner(",");
            movieData.getActors().forEach((e) -> sjActor.add(e.getCreditsID().toString()));
            atts[PersistentMovieMapper.CSVPositions.ACTORS.ordinal()] = sjActor.toString();
        } else {
            atts[PersistentMovieMapper.CSVPositions.ACTORS.ordinal()] = " ";
        }

        if(movieData.getScreenwriter().size() != 0) {
            StringJoiner sjScreenwriter = new StringJoiner(",");
            movieData.getScreenwriter().forEach((e) -> sjScreenwriter.add(e.getCreditsID().toString()));
            atts[PersistentMovieMapper.CSVPositions.SCREENWRITERS.ordinal()] = sjScreenwriter.toString();
        } else {
            atts[PersistentMovieMapper.CSVPositions.SCREENWRITERS.ordinal()] = " ";
        }
        return atts;
    }

    public static String[] getCSVHeader() {
        return new String[]{PersistentMovieMapper.CSVPositions.MOVIEID.name(), PersistentMovieMapper.CSVPositions.TITEL.name(), PersistentMovieMapper.CSVPositions.GENRE.name(),
                PersistentMovieMapper.CSVPositions.RELEASEYEAR.name(), PersistentMovieMapper.CSVPositions.METADATA.name(), PersistentMovieMapper.CSVPositions.RUNNINGTIMEINMIN.name(),
                PersistentMovieMapper.CSVPositions.DIRECTORS.name(), PersistentMovieMapper.CSVPositions.ACTORS.name(), PersistentMovieMapper.CSVPositions.SCREENWRITERS.name()};
    }
}
