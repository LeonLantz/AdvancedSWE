package org.movie.manager.plugin.gui;

import org.movie.manager.domain.FilmProfessional.FilmProfessionalID;
import org.movie.manager.domain.Metadata.IMBDdata;
import org.movie.manager.domain.Metadata.MetadataID;
import org.movie.manager.domain.Metadata.Ownership;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class GUIEditMovie extends ObservableComponent {

    CustomTextField movieIDField, titelField, genreField, releaseYearField, runningTimeInMinField, metadataIDField;

    // Collection<FilmProfessionalID> directorIDs, Collection<FilmProfessionalID> actorIDs, Collection<FilmProfessionalID> screenwriterIDs)
    CustomTextField metaDataIDField, ownershipField, nameOrMediumField, descriptionField, imdbIDField, imbdRatingField, imbdMetascoreField, ownRatingField;
    //MetadataID metadataID, Availability availability, IMBDdata imbDdata, Rating ownRating, MovieID movie
//    private String iMDBID;
//    /*
//    Must be in its own aggregate, not part of a film.
//    There can be a rating for the film, but it is not necessarily part of it.
//    Especially if the film is new, this always has no rating.
//    */
//
//    private double iMDBRating;
//
//    // METASCORE is a weighted average of reviews from top critics and publications for a given movie
//    private int metascore;
    JPanel moviePanel, metadataPanel, filmProfessionalPanel;

    public GUIEditMovie(Movie movie) {
        this.setLayout(new BorderLayout(0,0));

        moviePanel = new JPanel();
        moviePanel.setPreferredSize(new Dimension(300, 500));
        titelField = new CustomTextField("Titel", "Title of the movie");
        genreField = new CustomTextField("Genre", "Genre of the movie");
        releaseYearField = new CustomTextField("Year of publication", "Release year of the movie");
        runningTimeInMinField = new CustomTextField("Running time", "Running time of the movie");
        moviePanel.add(titelField);
        moviePanel.add(genreField);
        moviePanel.add(releaseYearField);
        moviePanel.add(runningTimeInMinField);


        metadataPanel = new JPanel();
        metadataPanel.setPreferredSize(new Dimension(300, 500));
        //TODO Dropdown
        ownershipField = new CustomTextField("Ownership", "Availability of the movie");
        nameOrMediumField = new CustomTextField("Name or Medium", "Name or Medium of the movie");
        descriptionField = new CustomTextField("Description", "Description of the movie");
        imdbIDField = new CustomTextField("IMDb ID", "IMDb data of the movie");
        imbdRatingField = new CustomTextField("IMDb rating", "IMDb data of the movie");
        imbdMetascoreField = new CustomTextField("IMDb metascore", "IMDb data of the movie");
        //TODO Dropdown
        ownRatingField = new CustomTextField("Own rating", "Own rating of the movie");
        moviePanel.add(ownershipField);
        moviePanel.add(nameOrMediumField);
        metadataPanel.add(descriptionField);
        metadataPanel.add(imdbIDField);
        metadataPanel.add(imbdRatingField);
        metadataPanel.add(imbdMetascoreField);
        metadataPanel.add(ownRatingField);


        filmProfessionalPanel = new JPanel();
        filmProfessionalPanel.setPreferredSize(new Dimension(300, 500));
        filmProfessionalPanel.setBackground(Color.green);

        this.add(moviePanel, BorderLayout.WEST);
        this.add(metadataPanel, BorderLayout.CENTER);
        this.add(filmProfessionalPanel, BorderLayout.EAST);

        //TODO movieID
        titelField = new CustomTextField("Titel", "Title of the movie");
        genreField = new CustomTextField("Genre", "Genre of the movie");
        releaseYearField = new CustomTextField("Year of publication", "Release year of the movie");
        runningTimeInMinField = new CustomTextField("Running time", "Running time of the movie");



    }
}
