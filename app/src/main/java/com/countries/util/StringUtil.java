package com.countries.util;

import android.content.Context;

/**
 * @author Tosin Onikute.
 */

public class StringUtil {

    private Context mContext;

    public StringUtil(Context context) {
        mContext = context;
    }

    public static boolean isBlank(CharSequence string) {
        return (string == null || string.toString().trim().length() == 0);
    }

    public static String toTitleCase(String str) {
        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }
}
