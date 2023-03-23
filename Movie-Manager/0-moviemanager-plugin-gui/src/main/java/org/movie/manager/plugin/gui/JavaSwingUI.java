package org.movie.manager.plugin.gui;

import org.movie.manager.adapters.Events.*;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Persistable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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
    private JLabel headlineLabel, dhbwImageLabel;

    public JavaSwingUI() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout(0,0));

        //Header
        headerPanel = new JPanel(new BorderLayout(0,0));
        headerPanel.setPreferredSize(new Dimension(900, 70));
        headerPanel.setBackground(Color.lightGray);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        headlineLabel = new JLabel("Movie Manager");
        headlineLabel.setFont(new Font(Font.SANS_SERIF, 1, 30));
        headlineLabel.setBorder(new EmptyBorder(0,50,0,0));
        dhbwImageLabel = new JLabel(getDHBWImage());
        dhbwImageLabel.setPreferredSize(new Dimension(200, 70));
        headerPanel.add(dhbwImageLabel, BorderLayout.EAST);
        headerPanel.add(headlineLabel, BorderLayout.CENTER);

        //Content
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

        //Footer
        footerPanel = new JPanel(new BorderLayout(0,0));
        footerPanel.setPreferredSize(new Dimension(900, 70));
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setBorder(BorderFactory.createMatteBorder(3,0,0,0, Color.BLACK));

        //Table
        String[] columnNames = {"Title", "Genre", "Jahr", "Laufzeit(min)"};
        tableComponent = new TableComponent(Movie.class, columnNames, this);
        tableComponent.setPreferredSize(new Dimension(800, 460));
        contentPanel.add(tableComponent, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private ImageIcon getDHBWImage() {
        ImageIcon imageIcon = null;
        try {
            BufferedImage myPicture = ImageIO.read(getFileFromResource("dhbw.png"));
            imageIcon = new ImageIcon(myPicture);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return imageIcon;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    @Override
    public void processGUIEvent(GUIEvent ge) {
        fireGUIEvent(ge);
    }

    @Override
    public void processUpdateEvent(UpdateEvent event) {
        System.out.println(event.getCmdText());
        if(event.getCmdText().equals("Controller.setMovies")) {
            Collection<Movie> m = (Collection) event.getData();
            this.tableComponent.setData(m);
        }else if(event.getCmdText().equals("Controller.setDetailData")) {
            ArrayList<Persistable> allMovieData = (ArrayList<Persistable>)event.getData();
            IOUtilities.openInJDialog(new GUIEditMovie((Movie)allMovieData.get(0), (Metadata) allMovieData.get(1), (Collection<FilmProfessional>) allMovieData.get(2)), 900, 500, 350, 250, "Movie Manager", null, false);
            this.tableComponent.removeSelection();
        }

    }
}
