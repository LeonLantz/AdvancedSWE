package org.movie.manager.domain;

import java.util.UUID;

public class Credits {

    private final UUID creditsID; //only getFunction()
    private final String firstName;
    private String secondName;
    private String biography; // Anything that might be interesting about a person: e.g. birthday, birthCountry, NumberOscars, etc.

    public Credits(UUID creditsID, String firstName, String secondName, String biography) {
        this.creditsID = creditsID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.biography = biography;
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
}
