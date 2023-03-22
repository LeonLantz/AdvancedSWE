package org.movie.manager.plugin.gui;

import org.movie.manager.adapters.Events.*;
import org.movie.manager.domain.Movie.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class JavaSwingUI extends ObservableComponent implements IGUIEventListener, IUpdateEventListener {

    public enum Commands implements EventCommand {

        GET_MOVIES( "JavaSwingUI.getMovies", Movie[].class );
        public final Class<?> payloadType;
        public final String cmdText;

        private Commands( String cmdText, Class<?> payloadType ) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return null;
        }

        @Override
        public Class<?> getPayloadType() {
            return null;
        }
    }

    TableComponent tableComponent;

    public JavaSwingUI() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout(0,0));

        //Table
        String[] columnNames = {"Title", "Genre", "Jahr", "Laufzeit(min)"};
        tableComponent = new TableComponent(Movie.class, columnNames);
        this.add(tableComponent);
    }

    @Override
    public void processGUIEvent(GUIEvent event) {
        fireGUIEvent(new GUIEvent(this, Commands.GET_MOVIES, null));
    }

    @Override
    public void processUpdateEvent(UpdateEvent event) {
        Collection<Movie> m = (Collection) event.getData();
        this.tableComponent.setData(m);
    }
}
