package com.jukusoft.letterbox.utils;

public class ImportUtils {

    private ImportUtils() {
        //
    }

    public static boolean isInitialImportEnabled() {
        //TODO: use spring @Value instead
        return Boolean.parseBoolean(System.getProperty("initial.import.enabled", "true"));
    }

}
