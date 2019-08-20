package ru.mdashlw.hypixel.exceptions;

/**
 * Your API key has been throttled by the web server,
 * means you have made over 120 queries in one minute.
 * Abuse of the API will lead to your API key being banned.
 */
public final class HypixelApiThrottleException extends HypixelApiException {
    public HypixelApiThrottleException() {
        super("API throttle!");
    }
}
