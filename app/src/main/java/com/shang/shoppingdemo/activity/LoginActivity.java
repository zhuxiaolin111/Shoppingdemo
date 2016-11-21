package com.shang.shoppingdemo.activity;

import com.shang.shoppingdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author wang
 * @see 登录页
 *
 */
 
public class LoginActivity extends Activity implements OnClickListener {

	TextView tvForgetPassword;// 忘记密码
	TextView tvRegister;// 注册
	Button btLogin;// 登录按钮

	Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initView();// 初始化控件
	}

	private void initView() {
		tvForgetPassword = (TextView) findViewById(R.id.tv_login_forgetPassword);
		tvRegister = (TextView) findViewById(R.id.tv_login_register);
		btLogin = (Button) findViewById(R.id.bt_login);

		tvForgetPassword.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
		btLogin.setOnClickListener(this);

	
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_login_forgetPassword:
			// 跳转到绑定邮箱验证界面
			mIntent = new Intent(LoginActivity.this, BindingEmailActivity.class);
			startActivity(mIntent);
			finish();
			break;
		// 点击注册按钮，跳转到注册界面
		case R.id.tv_login_register:
			mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(mIntent);
			finish();
			break;
		case R.id.bt_login:

			break;

		default:
			break;
		}
	}

}
