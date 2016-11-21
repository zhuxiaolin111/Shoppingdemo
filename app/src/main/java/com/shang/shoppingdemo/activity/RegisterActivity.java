package com.shang.shoppingdemo.activity;

import com.shang.shoppingdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author wang
 * @see 注册页
 *
 */
public class RegisterActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Button btRegister = (Button) findViewById(R.id.bt_register_register);
		//注册按钮点击事件
		btRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//跳转到绑定手机号页面
				Intent intent = new Intent(RegisterActivity.this, BindingPhoneActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
