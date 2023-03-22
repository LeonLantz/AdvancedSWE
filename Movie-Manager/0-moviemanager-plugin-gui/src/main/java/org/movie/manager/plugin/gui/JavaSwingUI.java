package org.movie.manager.plugin.gui;

import org.movie.manager.adapters.Events.*;
import org.movie.manager.domain.Movie.Movie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Style;
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

    private TableComponent tableComponent;
    private JPanel headerPanel, contentPanel, footerPanel, marginLPanel, marginRPanel;
    private JLabel headlineLabel;

    public JavaSwingUI() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout(0,0));

        headerPanel = new JPanel(new BorderLayout(0,0));
        headerPanel.setPreferredSize(new Dimension(900, 70));
        headerPanel.setBackground(Color.lightGray);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        headlineLabel = new JLabel("Movie Manager");
        headlineLabel.setFont(new Font(Font.SANS_SERIF, 1, 30));
        headlineLabel.setBorder(new EmptyBorder(0,50,0,0));
        headerPanel.add(headlineLabel, BorderLayout.CENTER);

        contentPanel = new JPanel(new BorderLayout(0,0));
        contentPanel.setPreferredSize(new Dimension(900, 460));
        contentPanel.setBackground(Color.green);
        marginLPanel = new JPanel();
        marginLPanel.setPreferredSize(new Dimension(50, 460));
        marginLPanel.setBackground(Color.lightGray);
        marginLPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,3, Color.BLACK));
        contentPanel.add(marginLPanel, BorderLayout.WEST);
        marginRPanel = new JPanel();
        marginRPanel.setPreferredSize(new Dimension(50, 460));
        marginRPanel.setBackground(Color.lightGray);
        marginRPanel.setBorder(BorderFactory.createMatteBorder(0,3,0,0, Color.BLACK));
        contentPanel.add(marginRPanel, BorderLayout.EAST);

        footerPanel = new JPanel(new BorderLayout(0,0));
        footerPanel.setPreferredSize(new Dimension(900, 70));
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setBorder(BorderFactory.createMatteBorder(3,0,0,0, Color.BLACK));



        //Table
        String[] columnNames = {"Title", "Genre", "Jahr", "Laufzeit(min)"};
        tableComponent = new TableComponent(Movie.class, columnNames);
        tableComponent.setPreferredSize(new Dimension(800, 460));
        contentPanel.add(tableComponent, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

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
