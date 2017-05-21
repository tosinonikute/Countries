package com.countries.util;

import android.util.Log;

/**
 * @author Tosin Onikute.
 */

public class Logger {

    final String tag;
    boolean enable = true;

    public static Logger getLogger(Class cls) {
        return new Logger(cls);
    }

    public static Logger getLogger(String tag) {
        return new Logger(tag);
    }

    public Logger(String tag) {
        this.tag = tag;
    }

    public Logger(Class cls) {
        this.tag = cls.getName();
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void debug(String msg) {
        if (isEnable()) {
            Log.d(tag, msg);
        }
    }

    public void debug(String msg, Object... args) {
        debug(String.format(msg, args));
    }

    public void warn(String msg) {
        if (isEnable()) {
            Log.w(tag, msg);
        }
    }

    public void warn(Throwable throwable) {
        warn(Log.getStackTraceString(throwable));
    }

    public void warn(String msg, Object... args) {
        warn(String.format(msg, args));
    }

    public void info(String msg) {
        if (isEnable()) {
            Log.i(tag, msg);
        }
    }

    public void info(String msg, Object... args) {
        info(String.format(msg, args));
    }

    public void error(String msg) {
        if (isEnable()) {
            Log.e(tag, msg);
        }
    }

    public void error(Throwable throwable) {
        error(Log.getStackTraceString(throwable));
    }

    public void error(String msg, Object... args) {
        error(String.format(msg, args));
    }

    public void verbose(String msg) {
        if (isEnable()) {
            Log.v(tag, msg);
        }
    }

    public void verbose(String msg, Object... args) {
        verbose(String.format(msg, args));
    }
}
