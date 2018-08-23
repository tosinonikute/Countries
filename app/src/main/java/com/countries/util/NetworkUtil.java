package com.countries.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Tosin Onikute.
 */

public class NetworkUtil {

    public static boolean isConnected(Context context) {
        final ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivity != null && connectivity.getActiveNetworkInfo() != null && connectivity.getActiveNetworkInfo().isConnected();
    }



}
