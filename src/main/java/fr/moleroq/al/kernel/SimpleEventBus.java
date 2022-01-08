package fr.moleroq.al.kernel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class SimpleEventBus<E extends Event> implements EventBus<E> {

    private final Map<Class<? extends E>, List<EventListener<? extends E>>> associatedListeners = new HashMap<>();

    @Override
    public void publish(E event) {
        List<EventListener<? extends E>> eventListeners = this.associatedListeners.get(event.getClass());
        if (eventListeners == null) {
            return;
        }

        for (EventListener eventListener : eventListeners) {
            eventListener.listenTo(event);
        }
    }

    @Override
    public void register(Class<? extends E> classE, List<EventListener<? extends E>> eventListeners) {
        this.associatedListeners.put(classE, eventListeners);
    }

    @Override
    public String toString() {
        return "SimpleEventBus{" +
                "associatedListeners=" + associatedListeners +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleEventBus<?> that = (SimpleEventBus<?>) o;
        return Objects.equals(associatedListeners, that.associatedListeners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(associatedListeners);
    }
}
