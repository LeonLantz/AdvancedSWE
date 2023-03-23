package org.movie.manager.plugin.gui;

import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GUIEditMovie extends ObservableComponent {

    CustomTextField titelField, genreField, releaseYearField, runningTimeInMinField;

    CustomTextField ownershipField, nameOrMediumField, descriptionField, imdbIDField, imbdRatingField, imbdMetascoreField, ownRatingField;

    JPanel moviePanel, metadataPanel, filmProfessionalPanel;

    JButton editSaveButton, imdbButton;

    public GUIEditMovie(Movie movie, Metadata metadata, Collection<FilmProfessional> filmProfessionals) {
        this.setLayout(new BorderLayout(0,0));
        initInputFields();
        if(movie != null) {
            titelField.setValue(movie.getTitel());
            genreField.setValue(movie.getGenre());
            releaseYearField.setValue(String.valueOf(movie.getReleaseYear()));
            runningTimeInMinField.setValue(String.valueOf(movie.getRunningTimeInMin()));
            ownershipField.setValue(metadata.getAvailability().getOwnership().toString()); //TODO Dropdown
            nameOrMediumField.setValue(metadata.getAvailability().getNameOrMedium());
            descriptionField.setValue(metadata.getAvailability().getDescription());
            imdbIDField.setValue(metadata.getImbDdata().getiMDBID());
            imbdRatingField.setValue(String.valueOf(metadata.getImbDdata().getiMDBRating()));
            imbdMetascoreField.setValue(String.valueOf(metadata.getImbDdata().getMetascore()));
            ownRatingField.setValue(String.valueOf(metadata.getOwnRating().getRating()));
            //TODO fill remaining fields
            editSaveButton = new JButton("Edit");
            editSaveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(editSaveButton.getText() == "Edit") {
                        editSaveButton.setText("Save");
                    }else {
                        editSaveButton.setText("Edit");
                    }
                }
            });
            filmProfessionalPanel.add(editSaveButton, BorderLayout.SOUTH);
        } else {
            imdbIDField.setValue("not set");
            imbdMetascoreField.setValue("not set");
            imbdRatingField.setValue("not set");
            editSaveButton = new JButton("Save");
            editSaveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Save movie");
                    saveNewMovie();
                }
            });
            filmProfessionalPanel.add(editSaveButton, BorderLayout.SOUTH);

            imdbButton = new JButton("IMDb Integration");
            imdbButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            metadataPanel.add(imdbButton);
        }
    }

    private void initInputFields() {
        //Movie Panel (west)
        moviePanel = new JPanel();
        moviePanel.setPreferredSize(new Dimension(300, 500));
        titelField = new CustomTextField("Titel", "Title of the movie");
        genreField = new CustomTextField("Genre", "Genre of the movie");
        releaseYearField = new CustomTextField("Year of publication", "Release year of the movie");
        runningTimeInMinField = new CustomTextField("Running time (min)", "Running time of the movie");
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
        //TODO Dropdown
        ownRatingField = new CustomTextField("Own rating", "Own rating of the movie");
        imdbIDField = new CustomTextField("IMDb ID", "IMDb data of the movie");
        imbdRatingField = new CustomTextField("IMDb rating", "IMDb data of the movie");
        imbdMetascoreField = new CustomTextField("IMDb metascore", "IMDb data of the movie");

        moviePanel.add(ownershipField);
        moviePanel.add(nameOrMediumField);
        metadataPanel.add(descriptionField);
        metadataPanel.add(ownRatingField);
        metadataPanel.add(imdbIDField);
        metadataPanel.add(imbdRatingField);
        metadataPanel.add(imbdMetascoreField);


        //FilmProfessional Panel (east)
        filmProfessionalPanel = new JPanel();
        filmProfessionalPanel.setPreferredSize(new Dimension(300, 500));
        filmProfessionalPanel.setBackground(Color.green);

        SimpleListComponent simpleListComponent = new SimpleListComponent("Film Professionals");
        filmProfessionalPanel.add(simpleListComponent, BorderLayout.CENTER);

        this.add(moviePanel, BorderLayout.WEST);
        this.add(metadataPanel, BorderLayout.CENTER);
        this.add(filmProfessionalPanel, BorderLayout.EAST);
    }

    private boolean saveNewMovie() {
        Movie movie = new Movie(null, titelField.getValue(), genreField.getValue(), Integer.valueOf(releaseYearField.getValue()), Integer.valueOf(runningTimeInMinField.getValue()), null, null, null, null);
        return false;
    }
}
