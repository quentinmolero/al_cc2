package fr.moleroq.al.application;

import fr.moleroq.al.domain.ErrorMessage;
import fr.moleroq.al.kernel.Engine;

@Engine
public final class ErrorEngine {

    private static final ErrorEngine INSTANCE = new ErrorEngine();

    private ErrorEngine() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already initialized");
        }
    }

    public static ErrorEngine getInstance() {
        return INSTANCE;
    }

    public ErrorMessage createError(String message) {
        return new ErrorMessage(message);
    }
}
