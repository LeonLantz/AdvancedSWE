package org.movie.manager.adapters.Events;

import java.util.EventListener;

public interface IGUIEventListener extends EventListener {
    void processGUIEvent(GUIEvent event);
}
