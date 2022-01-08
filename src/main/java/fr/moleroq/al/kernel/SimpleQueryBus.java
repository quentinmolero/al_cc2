package fr.moleroq.al.kernel;

import java.util.Map;
import java.util.Objects;

public final class SimpleQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler> dataMap;

    public SimpleQueryBus(Map<Class<? extends Query>, QueryHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public <Q extends Query, R> R send(Q query) {

        final QueryHandler queryHandler = dataMap.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }

    @Override
    public String toString() {
        return "SimpleQueryBus{" +
                "dataMap=" + dataMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleQueryBus that = (SimpleQueryBus) o;
        return Objects.equals(dataMap, that.dataMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataMap);
    }
}
