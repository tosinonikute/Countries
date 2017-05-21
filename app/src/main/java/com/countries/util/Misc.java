package com.countries.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.format.DateUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Tosin Onikute.
 */

public class Misc {

    private Context mContext;

    public Misc(Context context) {
        mContext = context;
    }

    public static CharSequence formatTime(long hnTimestamp) {
        hnTimestamp = 1000 * hnTimestamp;
        CharSequence HNtime = DateUtils.getRelativeTimeSpanString(hnTimestamp,
                System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        return HNtime;
    }

    public static void displayLongToast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void displayLongToast(Context context, @StringRes int ResId) {
        Toast.makeText(context, ResId, Toast.LENGTH_LONG).show();
    }

    public static void setSnackBarTextColor(Snackbar snackbar, Context context, @ColorRes int color) {
        TextView snackbarText = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarText.setTextColor(context.getResources().getColor(color));
    }





}
