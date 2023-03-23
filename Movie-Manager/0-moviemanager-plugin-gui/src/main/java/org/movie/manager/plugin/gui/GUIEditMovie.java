package org.movie.manager.plugin.gui;

import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Movie.Movie;
import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class GUIEditMovie extends ObservableComponent {

    CustomTextField titelField, genreField, releaseYearField, runningTimeInMinField;

    CustomTextField ownershipField, nameOrMediumField, descriptionField, imdbIDField, imbdRatingField, imbdMetascoreField, ownRatingField;

    JPanel moviePanel, metadataPanel, filmProfessionalPanel;

    public GUIEditMovie(Movie movie, Metadata metadata, Collection<FilmProfessional> filmProfessionals) {
        this.setLayout(new BorderLayout(0,0));
        initInputFields();
        if(movie != null) {
            titelField.setValue(movie.getTitel());
            genreField.setValue(movie.getGenre());
            releaseYearField.setValue(String.valueOf(movie.getReleaseYear()));
            runningTimeInMinField.setValue(String.valueOf(movie.getRunningTimeInMin()));
            //TODO fill remaining fields
        }
    }

    private void initInputFields() {
        //Movie Panel (west)
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

        //Metadata Panel (center)
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

        //FilmProfessional Panel (east)
        filmProfessionalPanel = new JPanel();
        filmProfessionalPanel.setPreferredSize(new Dimension(300, 500));
        filmProfessionalPanel.setBackground(Color.green);

        this.add(moviePanel, BorderLayout.WEST);
        this.add(metadataPanel, BorderLayout.CENTER);
        this.add(filmProfessionalPanel, BorderLayout.EAST);
    }
}
