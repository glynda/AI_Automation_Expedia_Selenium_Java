package com.expedia.automation.support;

/** Expedia replaced the page with a bot check. Tests do not solve or bypass it. */
public final class BotCheckException extends RuntimeException {

    public BotCheckException() {
        super("Expedia showed a bot check. This suite does not solve or bypass it.");
    }
}
