package org.projects.GUI.utils;

import java.text.Normalizer;

public class HashName {
    public static String convertToSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String noDiacritics = normalized.replaceAll("\\p{M}", "");

        return noDiacritics.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return lastIndex == -1 ? "" : fileName.substring(lastIndex);
    }
}
