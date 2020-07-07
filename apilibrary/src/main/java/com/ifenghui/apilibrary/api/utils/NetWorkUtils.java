package com.ifenghui.apilibrary.api.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by colin on 2016/1/6.
 */
public class NetWorkUtils {
    private static final String TAG = "NetworkUtil";

    protected static String convertStreamToString(InputStream is) {

        if (is == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8000);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean dataConnected(Context context) {
        Log.i(TAG, "check dataConnected");
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();

            }

        }
        return false;
    }

    /**
     * 判断网络类型
     */
    public static boolean is2G(Context mContext) {
        //Log.i(TAG, "is2G");
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getState() == NetworkInfo.State.CONNECTED && info.getType() == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = info.getExtraInfo().toUpperCase();
            String subTypeName = info.getSubtypeName().toUpperCase();
            //Log.i(TAG, "getExtraInfo:" + info.getExtraInfo());
            //Log.i(TAG, "getSubtypeName:" + info.getSubtypeName());
            //Log.i(TAG, "getTypeName:" + info.getTypeName());
            // uninet,cmnet
            if ((subTypeName.indexOf("GPRS") > -1 || subTypeName.indexOf("EDGE") > -1) && extraInfo.indexOf("NET") > -1 && extraInfo.indexOf("3GNET") == -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean is3G(Context mContext) {
        //Log.i(TAG, "is3G");
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getState() == NetworkInfo.State.CONNECTED && info.getType() == ConnectivityManager.TYPE_MOBILE) {
            // 接入点设置
            String extraInfo = info.getExtraInfo().toUpperCase();
            //String subTypeName = info.getSubtypeName().toUpperCase();
            //Log.i(TAG, "getExtraInfo:" + info.getExtraInfo());
            //Log.i(TAG, "getSubtypeName:" + info.getSubtypeName());
            //Log.i(TAG, "getTypeName:" + info.getTypeName());
            // 联通3G（3GNET），电信3G
            if (extraInfo.indexOf("3G") > -1 || extraInfo.indexOf("CTNET") > -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWap(Context mContext) {
        //Log.i(TAG, "isWap");
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getState() == NetworkInfo.State.CONNECTED && info.getType() == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = info.getExtraInfo().toUpperCase();
            //String subTypeName = info.getSubtypeName().toUpperCase();
            //Log.i(TAG, "getExtraInfo:" + info.getExtraInfo());
            //Log.i(TAG, "getSubtypeName:" + info.getSubtypeName());
            //Log.i(TAG, "getTypeName:" + info.getTypeName());
            // uniwap,cmwap,3gwap,ctwap
            if (extraInfo.indexOf("WAP") > -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifi(Context mContext) {
        //Log.i(TAG, "isWifi");
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getState() == NetworkInfo.State.CONNECTED && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
