package fr.moleroq.al.kernel;

public interface EventListener<E extends Event> {
    void listenTo(E event);
}
