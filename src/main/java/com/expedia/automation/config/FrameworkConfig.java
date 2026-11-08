package com.expedia.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Runtime settings. A system property overrides the matching key in config.properties. */
public final class FrameworkConfig {

    private static final Properties FILE = new Properties();

    static {
        try (InputStream input = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                FILE.load(input);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read config.properties", exception);
        }
    }

    private FrameworkConfig() {
    }

    public static String baseUrl() {
        String url = value("base.url", "https://www.expedia.com/");
        return url.endsWith("/") ? url : url + "/";
    }

    public static boolean headless() {
        return Boolean.parseBoolean(value("headless", "true"));
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(value("timeout.seconds", "20"));
    }

    private static String value(String key, String fallback) {
        String override = System.getProperty(key);
        if (override != null && !override.isBlank()) {
            return override.trim();
        }
        return FILE.getProperty(key, fallback).trim();
    }
}
