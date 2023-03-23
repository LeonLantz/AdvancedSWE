package org.movie.manager.plugin.gui;

import org.movie.manager.adapters.Events.EventCommand;
import org.movie.manager.adapters.Events.GUIEvent;
import org.movie.manager.adapters.Events.IGUIEventListener;
import org.movie.manager.domain.Metadata.MetadataID;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;
import org.movie.manager.domain.Persistable;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class TableComponent extends ObservableComponent {

    public enum Commands implements EventCommand {

        ROW_SELECTED( "TableComponent.rowSelected", MovieID.class );
        public final Class<?> payloadType;
        public final String cmdText;

        private Commands( String cmdText, Class<?> payloadType ) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return this.cmdText;
        }

        @Override
        public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }

    private JTable table;
    private DefaultTableModel tableModel;
    private Class tableClass;
    private String[] columnNames;
    private Collection<Persistable> tableData;
    private HashMap<Integer, Persistable> persistableElements;

    private final IGUIEventListener observer;

    public TableComponent(Class tableClass, String[] columnNames, IGUIEventListener observer) {
        this.tableClass = tableClass;
        this.columnNames = columnNames;
        this.observer = observer;
        this.addObserver(observer);
        this.tableData = null;

        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

        };

        this.initUI();
    }

    private void initUI() {
        this.table = new JTable(this.tableModel);
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.setLayout(new GridLayout());
        this.add(scrollPane);
        this.initTable();
    }

    private void initTable() {
        //Table editable
        this.table.setDefaultEditor(Object.class, null);

        this.table.getTableHeader().setPreferredSize(new Dimension(0, 50));
        this.table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        //Table selection mode
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = this.table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                TableComponent.this.handleSelectionEvent(e);
            }
        });

        //Table header
        table.getTableHeader().setReorderingAllowed(false);

        //Row height
        table.setRowHeight(40);

        //Border
        table.setBorder(BorderFactory.createEmptyBorder());
    }

    private void handleSelectionEvent(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            int row = this.table.getSelectedRow();
            if(row != -1) {
                this.fireGUIEvent(new GUIEvent(this, Commands.ROW_SELECTED, ((Movie)persistableElements.get(row)).getMovieID()));
            }
        }
    }

    public void removeSelection() {
        this.table.clearSelection();
    }

    public void setData(Collection tableData) {
        this.tableData = tableData;
        Vector<Vector<Attribute>> vectorData = this.convertAndMapPersistable();
        Vector<String> vectorColumns = new Vector<>();
        for(String s : columnNames) {
            vectorColumns.add(s);
        }
        this.table.setModel(new DefaultTableModel(vectorData, vectorColumns));
        table.setDefaultRenderer(Object.class, new AttributeTableCellRenderer());

        int numColumns = this.table.getColumnCount();
        for(int i = 0; i < numColumns; i++) {
            TableColumn tableColumn = this.table.getColumnModel().getColumn(i);
            tableColumn.setMinWidth(200);
            tableColumn.setMaxWidth(200);
        }

        this.table.updateUI();
    }

    private Vector<Vector<Attribute>> convertAndMapPersistable() {
        this.persistableElements = new HashMap(this.tableData.size());
        Vector<Vector<Attribute>> dataVec = new Vector(this.tableData.size());
        int[] ii = new int[1];
        this.tableData.forEach((e) -> {
            int var10004 = ii[0];
            int var10001 = ii[0];
            ii[0] = var10004 + 1;
            this.persistableElements.put(var10001, e);
            Vector<Attribute> vecTmp = new Vector();
            Attribute.filterVisibleAttributes(persistableToVector(e)).forEach((a) -> {
                vecTmp.add(a);
            });
            dataVec.add(vecTmp);
        });
        return dataVec;
    }

    private Vector<Attribute> persistableToVector(Persistable persistable) {
        Vector<Attribute> vec = new Vector();
        if (persistable instanceof Movie) {
            vec.add(new Attribute("movieID", persistable, MovieID.class, ((Movie) persistable).getMovieID(), null, false  ));
            vec.add(new Attribute("title", persistable, String.class, ((Movie) persistable).getTitel(), null, true  ));
            vec.add(new Attribute("genre", persistable, String.class, ((Movie) persistable).getGenre(), null, true  ));
            vec.add(new Attribute("releaseYear", persistable, Integer.class, ((Movie) persistable).getReleaseYear(), null, true  ));
            vec.add(new Attribute("runningTimeInMin", persistable, Integer.class, ((Movie) persistable).getRunningTimeInMin(), null, true  ));
            vec.add(new Attribute("metadataID", persistable, MetadataID.class, ((Movie) persistable).getMetadataID(), null, false  ));
            vec.add(new Attribute("directorIDs", persistable, Collection.class, ((Movie) persistable).getDirectorIDs(), null, false  ));
            vec.add(new Attribute("actorIDs", persistable, Collection.class, ((Movie) persistable).getActorIDs(), null, false  ));
            vec.add(new Attribute("screenwriterIDs", persistable, Collection.class, ((Movie) persistable).getScreenwriter(), null, false  ));
        }
        return vec;
    }

    private Persistable vectorToPersistable(Vector<Attribute> vector) {
        if(vector.get(0).getDedicatedInstance().getClass().equals(Movie.class)) {
            //TODO
        }
        return null;
    }
}
