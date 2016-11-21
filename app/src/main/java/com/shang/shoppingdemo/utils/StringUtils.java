package com.shang.shoppingdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class StringUtils {

	public static boolean isBlank(String value) {
		return null == value || "".equals(value.trim()) ? true : false;
	}
	
	public static boolean isEmpty(String obj) {
        return obj != null && !obj.equals("") ? false : true;
    }

	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}
}
