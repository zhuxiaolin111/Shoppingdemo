package com.shang.shoppingdemo.activity;

import com.shang.shoppingdemo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 
 * @author wang
 * 
 *@see 主程序入口
 *
 */
public class MainActivity extends Activity {

	
	private static final String SHAREDPREFERENCES_NAME = "my_pref";//本地存储登录状态

	private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

	private boolean mFirst;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取第一次登录常量
		mFirst = isFirstEnter(MainActivity.this, MainActivity.this.getClass()
				.getName());

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (!mFirst) {
					//如不等于第一次登录常量
					intent = new Intent(MainActivity.this, LoginActivity.class);
					startActivity(intent);
				} else {
					//如果是第一次登录
					intent = new Intent(MainActivity.this, IndexActivity.class);
					startActivity(intent);
				}
				startActivity(intent);
				finish();

			}
		}, 3000);

	}
	
	//判断是否第一次登录
	private boolean isFirstEnter(Context context, String className) {
		if (context == null || className == null
				|| "".equalsIgnoreCase(className))
			return false;
		String mResultStr = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE).getString(
				KEY_GUIDE_ACTIVITY, "");// 取得所有类名 如 com.my.MainActivity
		if (mResultStr.equalsIgnoreCase("false"))
			return false;
		else
			return true;
	}

}
