package com.shang.shoppingdemo.activity;

import com.shang.shoppingdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 
 * @author wang
 * @see引导页
 *
 */
public class IndexActivity extends Activity {

	private ViewPager viewPager;
	private ArrayList<View> pageViews;//图片集合
	private ImageView imageView;//圆点
	private ImageView[] imageViews;//图片
	private ViewGroup main;// 包裹滑动图片LinearLayout
	private ViewGroup group;// 包裹小圆点的LinearLayout

	private Button bt_index;
	
	private static final String SHAREDPREFERENCES_NAME = "my_pref";
	
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();

		/**
		 * 以后如果要增加一个引导页面，直接在这里添加
		 */
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.guide_page_item01, null));
		pageViews.add(inflater.inflate(R.layout.guide_page_item02, null));
		pageViews.add(inflater.inflate(R.layout.guide_page_item03, null));

		imageViews = new ImageView[pageViews.size()];
		main = (ViewGroup) inflater.inflate(R.layout.activity_index, null);
		group = (ViewGroup) main.findViewById(R.id.viewGroup);
		viewPager = (ViewPager) main.findViewById(R.id.guidePages);

		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(IndexActivity.this);
			//底部圆点大小
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20,20);
			//设置圆点外边距
			lp.setMargins(15,0,15,0);
			//imageView圆点配置设置好的属性
			imageView.setLayoutParams(lp);
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(R.drawable.dot_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.dot_normal);
			}
			group.addView(imageViews[i]);
		}

		setContentView(main);

		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());

	}

	// 指引页面数据适配器
	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			//这个方法，是从ViewGroup中移出当前View
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			
			((ViewPager) arg0).addView(pageViews.get(arg1));
			if (arg1 == pageViews.size() - 1) {
				bt_index = (Button) arg0.findViewById(R.id.bt_index);
				bt_index.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(IndexActivity.this,
								LoginActivity.class);
						startActivity(intent);
						//当执行点击事件时，执行一下逻辑，为此判断是否初次登录
						SharedPreferences settings = getSharedPreferences(SHAREDPREFERENCES_NAME, 0);
				        SharedPreferences.Editor editor = settings.edit();
				        editor.putString(KEY_GUIDE_ACTIVITY, "false");
				        editor.commit();
					}
				});
			}
			return pageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	// 指引页面更改事件监听器
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		//这个方法有一个参数position，代表哪个页面被选中。
		//当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），手指抬起来就会立即执行这个方法，
		//position就是当前滑动到的页面。
		
		//这种情况在onPageScrolled执行方法前就会立即执行。
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0].setBackgroundResource(R.drawable.dot_focused);

				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.dot_normal);
				}
			}

		}
	}

}
