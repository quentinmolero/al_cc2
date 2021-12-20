package fr.moleroq.al.domain;

public enum UserState {
    IN_CREATION(0),
    CREATED_WITH_ERROR(1),
    CREATED(2);

    private final int id;

    UserState(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
