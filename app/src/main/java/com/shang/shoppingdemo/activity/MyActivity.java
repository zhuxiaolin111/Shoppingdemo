package com.shang.shoppingdemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.applib.controller.HXSDKHelper;
import com.shang.shoppingdemo.R;
import com.shang.shoppingdemo.bean.User;
import com.shang.shoppingdemo.constants.CommonConstants;
import com.shang.shoppingdemo.utils.ActivityManager;
import com.shang.shoppingdemo.utils.SharePreferenceUtil;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("HandlerLeak")
public abstract class MyActivity extends FragmentActivity {
	
	private TextView titleView;
	private TextView leftView;
	private TextView rightView;
	private TextView rightLeftView;
	private ImageButton rightLeftImageView;
	private ImageButton rightImageView;
	private ImageButton leftImageView;
	/**
	 * 上下文对象?
	 */
	protected Context context;
	
	private SharePreferenceUtil preferenceUtil;
	
	private User user;
	
	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == leftView) {
				onLeftClick(v);
			} else if (v == rightView) {
				onRightClick(v);
			}else if( v == rightLeftImageView){
				onRightLeftImageViewClick(v);
			}else if( v == rightLeftView){
				onRightLeftClick(v);
			}else if( v == rightImageView){
				onRightImageViewClick(v);
			}else if( v == leftImageView){
				onLeftImageViewClick(v);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		ActivityManager.addActivity(this);
		
		preferenceUtil = SharePreferenceUtil.getInstance(this);
	}

	@Override
    protected void onResume() {
        super.onResume();
        // onresume时，取消notification显示
        HXSDKHelper.getInstance().getNotifier().reset();
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityManager.removeActivity(this);
	}

	public void setTitle(String title) {
		titleView = (TextView) findViewById(R.id.title);
		titleView.setText(title);
		titleView.setVisibility(View.VISIBLE);
	}
	
	public void setTitle(String title, int color) {
		titleView = (TextView) findViewById(R.id.title);
		titleView.setText(title);
		titleView.setTextColor(color);
		titleView.setVisibility(View.VISIBLE);
	}
	
	public void setTitleLeftText(String value) {
		leftView = (TextView) findViewById(R.id.title_left_text);
		leftView.setText(value);
		leftView.setVisibility(View.VISIBLE);
		
		leftView.setOnClickListener(clickListener);
	}
	
	public void setTitleLeftImg(int resid) {
		leftImageView = (ImageButton) findViewById(R.id.title_left_img);
		leftImageView.setBackgroundResource(resid);
		leftImageView.setVisibility(View.VISIBLE);
		
		leftImageView.setOnClickListener(clickListener);
	}
	
	public void setTitleRightText(String value,int color) {
		rightView = (TextView) findViewById(R.id.title_right_text);
		rightView.setText(value);
		rightView.setVisibility(View.VISIBLE);
		rightView.setTextColor(color);
		rightView.setOnClickListener(clickListener);
	}
	
	public void setTitleRightText(String value) {
	    rightView = (TextView) findViewById(R.id.title_right_text);
	    rightView.setText(value);
	    rightView.setVisibility(View.VISIBLE);
	    
	    rightView.setOnClickListener(clickListener);
	}
	
	public void setTitleRightLeftImg(int resid) {
		rightLeftImageView = (ImageButton) findViewById(R.id.title_right_left_img);
		rightLeftImageView.setBackgroundResource(resid);
		rightLeftImageView.setVisibility(View.VISIBLE);
		
		rightLeftImageView.setOnClickListener(clickListener);
	}
	
	public void setTitleRightLeftText(String value) {
		rightLeftView = (TextView) findViewById(R.id.title_right_left_text);
		rightLeftView.setText(value);
		rightLeftView.setVisibility(View.VISIBLE);
	    
		rightLeftView.setOnClickListener(clickListener);
	}
	
	public void setTitleRightImg(int resid) {
		rightImageView = (ImageButton) findViewById(R.id.title_right_img);
		rightImageView.setBackgroundResource(resid);
		rightImageView.setVisibility(View.VISIBLE);
		
		rightImageView.setOnClickListener(clickListener);
	}
	
	public TextView getLeftView() {
		return leftView;
	}

	public TextView getRightView() {
		return rightView;
	}

	public ImageButton getRightImageView() {
		return rightImageView;
	}

	public ImageButton getLeftImageView() {
		return leftImageView;
	}

	protected void onLeftClick(View view) {
		
	}
	protected void onRightClick(View view) {

	}
	protected void onRightLeftImageViewClick(View view) {
		
	}
	protected void onRightLeftClick(View view) {
		
	}
	protected void onRightImageViewClick(View view) {

	}
	protected void onLeftImageViewClick(View view) {
		finish();
	}
	
	// �?测用户名是否编辑合法
	public boolean checkMobile(String mobile) {
		if (TextUtils.isEmpty(mobile)) {
			Toast.makeText(this, R.string.user_name_empty, Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!mobile.matches("^1[3|4|5|7|8|9]{1}\\d{9}$")) {
			Toast.makeText(this, R.string.user_name_error,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	// �?测密码是否编辑合�?
	public boolean checkPwd(String pwd) {
		if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, R.string.password_empty, Toast.LENGTH_SHORT).show();
			return false;
		} else if(pwd.length() < 6) {
			Toast.makeText(this, R.string.password_error, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	// �?测验证码是否编辑合法
	public boolean checkCode(String code) {
		if (!code.matches("\\d{6}$")) {
			Toast.makeText(this, R.string.code_error, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	
	private static int isExit = 0;

	protected void exitBy2Click() {
		Timer tExit = null;
		if (isExit == 0) {
			Toast.makeText(this, "再按�?次�??出程�?", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = 0; // 取消�?�?
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任�?
			++isExit; // 准备�?�?
		} else {
			finish();
			ActivityManager.clearActivity();
//			System.exit(0);
		}
	}
	/**
	 * 进入页面
	 * 
	 * @param cla
	 *            启动的页�?
	 */
	public void enterPage(Class<?> cla) {
		if (cla == null) {
			return;
		}
		enterPage(cla, null);
	}

	/**
	 * 进入页面
	 * 
	 * @param cla
	 *            要启动的页面
	 * @param bundle
	 *            要传递的参数
	 */
	public void enterPage(Class<?> cla, Bundle bundle) {
		if (cla == null) {
			return;
		}
		enterPageForResult(cla, bundle, 0);
	}

	/**
	 * 进入页面
	 * 
	 * @param cla
	 * @param requestCode
	 *            请求�?
	 */
	public void enterPageForResult(Class<?> cla, int requestCode) {
		if (cla == null) {
			return;
		}
		enterPageForResult(cla, null, requestCode);
	}

	/**
	 * 进入页面
	 * 
	 * @param cla
	 * @param bundle
	 * @param requestCode
	 *            请求�?
	 */
	public void enterPageForResult(Class<?> cla, Bundle bundle, int requestCode) {
		if (cla == null) {
			return;
		}
		Intent intent = new Intent(context, cla);
		if (bundle != null) {
			intent.putExtra(CommonConstants.START_ACTIVITY_PUTEXTRA, bundle);
		}
		if (requestCode > 0) {
			startActivityForResult(intent, requestCode);
		} else {
			startActivity(intent);
		}
	}
	
	/**
	 * 显示软键�?
	 */
	public void openkeybord() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 隐藏软键�?
	 */
	public void hiddenKeyBoard() {

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (getCurrentFocus() != null) {
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	
	private View topView;
	
	protected boolean touchStatus = false;
	
	public void setTopView(View topView) {
		this.topView = topView;
	}

	float mLastY = -1;
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(null != topView) {
			if (mLastY == -1) {
				mLastY = ev.getRawY();
			}
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastY = ev.getRawY();
				touchStatus(false);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_MOVE:
				final float deltaY = ev.getRawY() - mLastY;
				mLastY = ev.getRawY();
				
				LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) topView.getLayoutParams();
				
				int top = layoutParams.topMargin;
				if(top > -1 * topView.getHeight() && deltaY < 0) {
					top = Math.max((int)(layoutParams.topMargin + deltaY), -1*topView.getHeight());
					top = Math.min(0, top);
					layoutParams.topMargin = top;
					topView.requestLayout();
					touchStatus(true);
					return true;
				} else if(top < 0 && deltaY > 0 && bodyIsTop()) {
					top = Math.max((int)(layoutParams.topMargin + deltaY), -1*topView.getHeight());
					top = Math.min(0, top);
					layoutParams.topMargin = top;
					topView.requestLayout();
					touchStatus(true);
					return true;
				}
				break;
			default:
				break;
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	public boolean bodyIsTop() {
		return true;
	}
	
	public void touchStatus(boolean status) {
		this.touchStatus = status;
	}
	
}
