package ru.mdashlw.hypixel.exceptions;

/**
 * Your API key has been throttled by the web server,
 * means you have made over 120 queries in one minute.
 * Abuse of the API will lead to your API key being banned.
 */
public class HypixelApiThrottleException extends Exception {
    public HypixelApiThrottleException() {
        super("API throttle!");
    }
}
