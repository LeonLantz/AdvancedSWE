package org.movie.manager.domain.Credits;

import org.movie.manager.domain.Persistable;
import org.movie.manager.domain.Movie.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.UUID;

public class Credits implements Persistable {

    private final UUID creditsID; //only getFunction()
    private final String firstName;
    private String secondName;
    private String biography; // Anything that might be interesting about a person: e.g. birthday, birthCountry, NumberOscars, etc.

    private Collection<Movie> movies = new ArrayList<>();

    public Credits(UUID creditsID, String firstName, String secondName, String biography, Collection<Movie> movies) {
        if(creditsID != null)
            this.creditsID = creditsID;
        else
            this.creditsID = UUID.randomUUID();

        this.firstName = firstName;
        this.secondName = secondName;
        this.biography = biography;
        if (movies != null) this.movies = movies;
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
