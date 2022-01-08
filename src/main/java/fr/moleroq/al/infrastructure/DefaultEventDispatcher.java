package fr.moleroq.al.infrastructure;

import fr.moleroq.al.kernel.Event;
import fr.moleroq.al.kernel.EventDispatcher;
import fr.moleroq.al.kernel.EventListener;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DefaultEventDispatcher<E extends Event> implements EventDispatcher<E> {

    private final Map<Class<E>, List<EventListener<E>>> eventListenersMap;

    public DefaultEventDispatcher(Map<Class<E>, List<EventListener<E>>> eventListenersMap) {
        this.eventListenersMap = eventListenersMap;
    }

    @Override
    public void dispatch(E event) {
        final List<EventListener<E>> eventListeners = eventListenersMap.get(event.getClass());
        if (eventListeners != null) {
            eventListeners.forEach(eEventListener -> eEventListener.listenTo(event));
        }
    }

    @Override
    public String toString() {
        return "DefaultEventDispatcher{" +
                "eventListenersMap=" + eventListenersMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultEventDispatcher<?> that = (DefaultEventDispatcher<?>) o;
        return Objects.equals(eventListenersMap, that.eventListenersMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventListenersMap);
    }
}
