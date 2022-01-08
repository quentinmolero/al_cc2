package fr.moleroq.al.kernel;

import java.util.Map;
import java.util.Objects;

public final class SimpleCommandBus implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler> dataMap;

    public SimpleCommandBus(Map<Class<? extends Command>, CommandHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public <C extends Command, R> R send(C command) {
        return dispatch(command);
    }

    private <C extends Command, R> R dispatch(C command) {
        final CommandHandler commandHandler = dataMap.get(command.getClass());
        if (commandHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }

        return (R) commandHandler.handle(command);
    }

    @Override
    public String toString() {
        return "SimpleCommandBus{" +
                "dataMap=" + dataMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCommandBus that = (SimpleCommandBus) o;
        return Objects.equals(dataMap, that.dataMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataMap);
    }
}
