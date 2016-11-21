package com.shang.shoppingdemo.utils;

import java.util.ArrayList;
import java.util.List;

import com.shang.shoppingdemo.bean.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("CommitPrefEdits")
public class SharePreferenceUtil {
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor editor;
	private static SharePreferenceUtil preferenceUtil;
	private String SHARED_KEY_USER = "shared_key_user";// user
	private String SHARED_KEY_ADDRESS = "shared_key_address";// user
	private String SHARED_KEY_MOBILE = "shared_key_mobile";
	private String SHARED_KEY_PASSWORD = "shared_key_password";
	private String SHARED_KEY_SEARCH = "shared_key_search";
	private String SHARED_KEY_OTHERLOGIN = " shared_key_otherlogin";//第三方登�?

	public SharePreferenceUtil(Context context) {
		mSharedPreferences = context.getSharedPreferences("voxry",
				Context.MODE_PRIVATE);
		editor = mSharedPreferences.edit();
	}

	public static SharePreferenceUtil getInstance(Context context) {
		if (preferenceUtil == null) {
			preferenceUtil = new SharePreferenceUtil(context);
		}
		return preferenceUtil;
	}

	private String SHARED_KEY_COOKIE = "shared_key_cookie";// cookie
	private String SHARED_KEY_LONGITUDE = "shared_key_longitude";// 经度
	private String SHARED_KEY_LATITUDE = "shared_key_latitude";// 维度
	private final String SHARED_KEY_STU_LOGINNAME = "shared_key_stu_loginName";
	private final String SHARED_KEY_TEACHER_LOGINNAME = "shared_key_teacher_loginName";

	// cookie
	public void setCookie(String cookie) {
		editor.putString(SHARED_KEY_COOKIE, cookie);
		editor.commit();
	}

	public String getCookie() {
		return mSharedPreferences.getString(SHARED_KEY_COOKIE, "");
	}

	// 获取经纬�?
	public String getLatitude() {
		return mSharedPreferences.getString(SHARED_KEY_LATITUDE, "0");
	}

	public void setLatitude(double latitude) {
		editor.putString(SHARED_KEY_LATITUDE, String.valueOf(latitude));
		editor.commit();
	}

	public String getLongitude() {
		return mSharedPreferences.getString(SHARED_KEY_LONGITUDE, "0");
	}

	public void setLongitude(double logitude) {
		editor.putString(SHARED_KEY_LONGITUDE, String.valueOf(logitude));
		editor.commit();
	}

	// 用户学生登录的手机号或ID
	public void setStuLoginID(String phone) {
		editor.putString(SHARED_KEY_STU_LOGINNAME, phone);
		editor.commit();
	}

	public String getStuLoginID() {
		return mSharedPreferences.getString(SHARED_KEY_STU_LOGINNAME, "");
	}

	// 用户老师登录的手机号或ID
	public void setTeacherLoginID(String phone) {
		editor.putString(SHARED_KEY_TEACHER_LOGINNAME, phone);
		editor.commit();
	}

	public String getTeacherLoginID() {
		return mSharedPreferences.getString(SHARED_KEY_TEACHER_LOGINNAME, "");
	}

	public void setUser(String user) {
		editor.putString(SHARED_KEY_USER, user);
		editor.commit();
	}

	public User getUser() {
		String s = mSharedPreferences.getString(SHARED_KEY_USER, null);
		if (!StringUtils.isBlank(s)) {
			return GsonHandler.getNoExportGson().fromJson(s, User.class);
		} else {
			return null;
		}
	}
	
	public void setAddress(String address) {
		editor.putString(SHARED_KEY_ADDRESS, address);
		editor.commit();
	}

	public void delUser() {
		editor.remove(SHARED_KEY_USER);
		editor.commit();
	}
	
	public void delAddr() {
		editor.remove(SHARED_KEY_ADDRESS);
		editor.commit();
	}

	// mobile
	public void setMobile(String mobile) {
		editor.putString(SHARED_KEY_MOBILE, mobile);
		editor.commit();
	}

	public String getMobile() {
		return mSharedPreferences.getString(SHARED_KEY_MOBILE, "");
	}

	// passWord
	public void setPassWord(String passWord) {
		editor.putString(SHARED_KEY_PASSWORD, passWord);
		editor.commit();

	}

	public String getPassWord() {
		return mSharedPreferences.getString(SHARED_KEY_PASSWORD, "");
	}

	public void saveList(List<String> list) {
		editor.putInt("list_size", list.size());
		for (int i = 0; i < list.size(); i++) {
			editor.putString(SHARED_KEY_SEARCH + i, list.get(i));
		}
		editor.commit();
	}

	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		int size = mSharedPreferences.getInt("list_size", 0);
		for (int i = 0; i < size; i++) {
			list.add(mSharedPreferences.getString(SHARED_KEY_SEARCH + i, null));
		}
		return list;
	}
	
	public void delList() {
		editor.remove(SHARED_KEY_SEARCH);
		editor.remove("list_size");
		editor.commit();
	}
	
}
