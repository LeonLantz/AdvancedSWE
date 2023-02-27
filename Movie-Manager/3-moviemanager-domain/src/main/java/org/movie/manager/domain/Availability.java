package org.movie.manager.domain;

public class Availability {

    private State state;
    private String nameOrMedium; //Friend's name, name of Streaming Media (owned) or Cassette, DVD
    private String description; //Quality (Poor, Good, Very Good), Resolution, Available for a limited time only

    public Availability(State state, String nameOrMedium, String description) {
        this.state = state;
        this.nameOrMedium = nameOrMedium;
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getNameOrMedium() {
        return nameOrMedium;
    }

    public void setNameOrMedium(String nameOrMedium) {
        this.nameOrMedium = nameOrMedium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
