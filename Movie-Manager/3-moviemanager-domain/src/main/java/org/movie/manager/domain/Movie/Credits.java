package org.movie.manager.domain.Movie;

import org.movie.manager.domain.IPersistable;

import java.util.Collection;
import java.util.UUID;

public class Credits implements IPersistable {

    private final UUID creditsID; //only getFunction()
    private final String firstName;
    private String secondName;
    private String biography; // Anything that might be interesting about a person: e.g. birthday, birthCountry, NumberOscars, etc.

    private Collection<Movie> movies;

    public static enum CSVPositions {
        CREDITSID,
        FIRSTNAME,
        SECONDNAME,
        BIOGRAPHY,

        MOVIES;

        private CSVPositions() {
        }
    }

    public Credits(UUID creditsID, String firstName, String secondName, String biography, Collection<Movie> movies) {
        this.creditsID = creditsID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.biography = biography;
        this.movies = movies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public UUID getCreditsID() {
        return creditsID;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Collection<Movie> getMovies() {
        return movies;
    }



    @Override
    public Object getPrimaryKey() {
        return creditsID;
    }
}
