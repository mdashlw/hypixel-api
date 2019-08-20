package ru.mdashlw.hypixel.reply;

public abstract class Reply<T> {
    private boolean success;
    private String cause;
    private boolean throttle;

    public abstract T getValue();

    public final boolean isSuccess() {
        return this.success;
    }

    public final String getCause() {
        return this.cause;
    }

    public final boolean isThrottle() {
        return this.throttle;
    }
}
