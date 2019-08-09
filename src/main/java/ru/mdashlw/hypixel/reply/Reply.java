package ru.mdashlw.hypixel.reply;

public abstract class Reply<T> {
    private boolean success;
    private String cause;
    private boolean throttle;

    public abstract T getValue();

    public boolean isSuccess() {
        return success;
    }

    public String getCause() {
        return cause;
    }

    public boolean isThrottle() {
        return throttle;
    }
}
