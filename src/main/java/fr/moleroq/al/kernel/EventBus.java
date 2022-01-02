package fr.moleroq.al.kernel;

import java.util.List;

public interface EventBus<E extends Event> {
    void publish(E event);

    void register(Class<? extends E> classE, List<EventListener<? extends E>> eventListeners);
}
