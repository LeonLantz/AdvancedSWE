package org.movie.manager.adapters.Events;

import java.util.EventListener;

public interface IUpdateEventListener extends EventListener {
    void processUpdateEvent(UpdateEvent event);
}
