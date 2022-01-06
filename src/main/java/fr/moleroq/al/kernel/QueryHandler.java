package fr.moleroq.al.kernel;

@FunctionalInterface
public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
