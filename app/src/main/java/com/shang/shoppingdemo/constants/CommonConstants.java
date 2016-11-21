package com.shang.shoppingdemo.constants;

import android.os.Environment;

public interface CommonConstants {

	/** 文件缓存目录 */
	public static final String SDPATH = Environment.getExternalStorageDirectory() + "/shopping/file/";
	
	/** 数据库缓存目录 */
	public static final String SDDBPATH = Environment.getExternalStorageDirectory() + "/shopping/db/";
	/**
	 * 外部存储目录路径
	 */
	public static final String STRATEGY_EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/voxry/";
	/**
	 * 内部存储目录
	 */ 
	public static final String STRATEGY_INNER_STORAGE_DIRECTORY = "/shopping/";
	
	public static final String READTOKEN = "wY6HQ2WLHV-Zt6L8KEI6Z-ubtmgdHzol";
	
	public static final String WRITETOKEN = "OJFoUHePRvpfagj-BziIXYsTkgcxIxXc";
	
	public static final String PRIVATEKEY = "xtbu19Rq63";
	
	public static final String USERID = "b077cc6adf";
	
	public static final int MAX_UPLOAD_FILE_SIZE = 6;
	
	public static final String ACTION_SCANNER_SUCCESS = "com.shang.shoppingdemo.talk" ;//广播
	
	public static final String ACTION_SCANNER_SUCCESS_CART = "com.shang.shoppingdemo.shopcart" ;//购物车
	
	public static final String ACTION_SCANNER_SUCCESS_HEAD = "com.shang.shoppingdemo.head" ;//头像
	
	/** 客服名称 */
	public static final String CUSTOM_SERVICE_NAME = "精美客服";
	
	/** 客服电话 */
	public static final String CUSTOM_SERVICE_MOBILE = "400-078-8066";
	
	/** 客服邮箱 */
	public static final String CUSTOM_SERVICE_EMAIL = "";
	/**
	 * 进入下一个页面传递Bundle参数的key值
	 */
	public static final String START_ACTIVITY_PUTEXTRA = "initValues";
	/**
	 * 进入下一个页面传递requestCode值
	 */
	public static final int START_ACTIVITY_REQUESTCODE1 = 0x01001;
	
	/** 图片地址 */
	public static final String PHOTO_URL = "http://m11.lanbaoapp.com/";
	
	/** 服务器接口地址 */
	public static final String HOST = "http://m11.lanbaoapp.com/";
	
	/** 服务器接口地址 */
	public static final String SERVER_URL = "http://m11.lanbaoapp.com/";
	public static final String PHOTO_URL_PUBLIC = "http://m11.lanbaoapp.com";
	
	
	
}
