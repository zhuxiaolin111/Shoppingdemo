package com.shang.shoppingdemo.adapter;


import com.shang.shoppingdemo.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * 
 * @author wang
 * @see 推荐关注页adapter
 *
 */
public class lvRecommendAdapter extends BaseAdapter {
	
	Context context;
	int  list = 4;
	

	public lvRecommendAdapter(Context context, int list) {
		super();
		this.context = context;
		this.list = list;
	}
	
	/* listView在开始绘制的时候，系统首先调用getCount（）函数，根据他的返回值得到listView的长 
	 * 度（这也是为什么在开始的第一张图特别的标出列表长度），然后根据这个长度，调用getView（）逐 
	 * 一绘制每一行。如果你的getCount（）返回值是0的话，列表将不显示同样return 1，就只显示一行。 
	 * 系统显示列表时，首先实例化一个适配器（这里将实例化自定义的适配器）。当手动完成适配时，必 
	 * 须手动映射数据，这需要重写getView（）方法。系统在绘制列表的每一行的时候将调用此方法。 
	 * getView()有三个参数，position表示将显示的是第几行，covertView是从布局文件中inflate来的 
	 * 布局。我们用LayoutInflater的方法将定义好的main.xml文件提取成View实例用来显示。然后 
	 * 将xml文件中的各个组件实例化（简单的findViewById()方法）。这样便可以将数据对应到各个组件 
	 * 上了。但是按钮为了响应点击事件，需要为它添加点击监听器，这样就能捕获点击事件。至此一个自定 
	 * 义的listView就完成了，现在让我们回过头从新审视这个过程。系统要绘制ListView了，他首先获得 
	 * 要绘制的这个列表的长度，然后开始绘制第一行，怎么绘制呢？调用getView()函数。在这个函数里面 
	 * 首先获得一个View（实际上是一个ViewGroup），然后再实例并设置各个组件，显示之。好了，绘制完 
	 * 这一行了。那 再绘制下一行，直到绘完为止。在实际的运行过程中会发现listView的每一行没有焦点 
	 * 了，这是因为Button抢夺了listView的焦点，只要布局文件中将Button设置为没有焦点就OK了*/ 

	@Override
	/*
	 * 获得项目（Item）的数量 
	 */
	public int getCount() {
		return list;
	}

	@Override
	/*
	 * 获得当前选项
	 */
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/*
	 * 获得当前选项的ID
	 */
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/*
	 * arg0position是指当前的位置
	 * arg1是指可以重用的视图，即刚刚出队的视图
	 */
	 
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
	//LinearLayout layout = find
		return null;
	}

	

}
