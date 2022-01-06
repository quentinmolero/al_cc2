package fr.moleroq.al.kernel;

public interface EventDispatcher<E extends Event>{
    void dispatch(E event);
}
