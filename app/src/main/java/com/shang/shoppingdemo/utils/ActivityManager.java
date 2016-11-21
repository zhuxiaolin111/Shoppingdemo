package com.shang.shoppingdemo.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityManager {
	
	private static List<Activity> activityList = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		activityList.add(activity);
	}
	
	public static void removeActivity(Activity activity) {
		activityList.remove(activity);
	}
	
	public static void clearActivity() {
		for(Activity activity : activityList) {
			activity.finish();
		}
		activityList.clear();
	}
}
