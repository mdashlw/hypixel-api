package ru.mdashlw.hypixel.util;

import java.util.regex.Pattern;

public final class StringUtil {
    public static final Pattern UNDERSCORE_DASH = Pattern.compile("[_-]");

    public static String stripUnderscoresAndDashes(final String s) {
        if (s == null) {
            return null;
        }

        return UNDERSCORE_DASH.matcher(s).replaceAll("");
    }

    public static String capitalize(final String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        final char[] chars = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
