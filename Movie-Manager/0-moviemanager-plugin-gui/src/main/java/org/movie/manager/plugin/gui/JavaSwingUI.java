package org.movie.manager.plugin.gui;

import org.movie.manager.adapters.Events.*;
import org.movie.manager.domain.Movie.Movie;

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

    public JavaSwingUI() {
    }

    @Override
    public void processGUIEvent(GUIEvent event) {
        fireGUIEvent(new GUIEvent(this, Commands.GET_MOVIES, null));
    }

    @Override
    public void processUpdateEvent(UpdateEvent event) {
        System.out.println(event.getData());
    }
}
