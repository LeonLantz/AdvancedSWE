package org.movie.manager.domain;

public class Credits {

    private String firstName;
    private String secondName;
    private String biography; // Anything that might be interesting about a person: e.g. birthday, birthCountry, NumberOscars, etc.

    public Credits(String firstName, String secondName, String biography) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.biography = biography;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
