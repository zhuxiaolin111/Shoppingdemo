package com.shang.shoppingdemo.activity;


import com.shang.shoppingdemo.R;
import com.shang.shoppingdemo.adapter.lvRecommendAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 
 * @author wang
 * @see 推荐关注界面
 */
public class RecommendedActivity extends Activity {
	
	ListView lvRecommend;//列表
	TextView tvRecommendBypass;//跳过
	TextView tvRecommend;//推荐关注
	lvRecommendAdapter adapter;
	int list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);
		
		initView();//初始化控件
	}

	private void initView() {
		 lvRecommend = (ListView) findViewById(R.id.lv_recommend);
		 tvRecommendBypass = (TextView) findViewById(R.id.tv_recommend_bypass);
		 tvRecommend = (TextView) findViewById(R.id.tv_recommend_recommend);
		 
		 
		 adapter = new lvRecommendAdapter(RecommendedActivity.this,list);
		 lvRecommend.setAdapter(adapter);
	}

	

}
