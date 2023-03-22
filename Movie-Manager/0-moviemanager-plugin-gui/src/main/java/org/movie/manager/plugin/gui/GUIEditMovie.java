package org.movie.manager.plugin.gui;

import org.movie.manager.domain.Movie.Movie;

import javax.swing.*;
import java.awt.*;

public class GUIEditMovie extends ObservableComponent {

    public GUIEditMovie(Movie movie) {
        this.setLayout(new BorderLayout(0,0));
        this.add(new JLabel(movie.getTitel()));
    }
}
