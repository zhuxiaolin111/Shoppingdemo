package com.shang.shoppingdemo.application;

import java.io.File;
import java.util.Timer;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import com.easefun.polyvsdk.PolyvSDKClient;
import com.easemob.EMCallBack;
import com.easemob.chatuidemo.DemoHXSDKHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.shang.shoppingdemo.R;
import com.shang.shoppingdemo.constants.CommonConstants;
import com.shang.shoppingdemo.service.PolyvAndroidService;

public class MyApplication extends Application {

	private File saveDir;
	
	public static Context applicationContext;

	private static MyApplication instance;
	
	public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();
	
	private static Timer timer;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		super.onCreate();
		
		applicationContext = this;
		instance = this;
		
		File cacheDir = new File(CommonConstants.SDPATH);
		if(!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		File dbCacheDir = new File(CommonConstants.SDDBPATH);
		if(!dbCacheDir.exists()) {
			dbCacheDir.mkdirs();
		}
		//CopyDataBase.copyDataBase(getApplicationContext(), CommonConstants.SDDBPATH + "/china.db", R.raw.china);
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.cacheInMemory(false)
		.cacheOnDisc(true)
				.showStubImage(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCacheExtraOptions(480, 800)
				.threadPoolSize(2)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
				// 1024)) // You can pass your own memory cache implementation/
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				// .discCacheFileNameGenerator(new Md5FileNameGenerator())//
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(getApplicationContext(),
								5 * 1000, 30 * 1000)) // connectTimeout (5 s),
														// readTimeout (30
														// s)��ʱʱ��
				.writeDebugLogs() // Remove for release app
				.defaultDisplayImageOptions(options).build();
		// Initialize ImageLoader with configuration.

		// Initialize ImageLoader with configuration
		ImageLoader.getInstance().init(config);
		initPolyvCilent();
		
		hxSDKHelper.onInit(this);
		
		JPushInterface.setDebugMode(true); 	// 设置�?启日�?,发布时请关闭日志
        JPushInterface.init(this);     		// 初始�? JPush
        
        ShareSDK.initSDK(getApplicationContext());
        
        //(this);
	}

	public void initPolyvCilent() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			saveDir = new File(CommonConstants.SDPATH);
			if (!saveDir.exists())
				saveDir.mkdir();
		}

		PolyvSDKClient client = PolyvSDKClient.getInstance();
		/*
		 * client.setReadtoken(); client.setWritetoken();
		 * client.setPrivatekey(); client.setUserId();
		 */
		client.setReadtoken(CommonConstants.READTOKEN);
		client.setWritetoken(CommonConstants.WRITETOKEN);
		client.setPrivatekey(CommonConstants.PRIVATEKEY);
		client.setUserId(CommonConstants.USERID);
		client.setDownloadDir(saveDir);

		client.startService(getApplicationContext(), PolyvAndroidService.class);
		// client.init();
		// System.out.println("private...:"+client.getPrivatekey());
		// client.setSign(true);

	}

	class AndroidConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
		}
	}

	public static MyApplication getInstance() {
		return instance;
	}
	
	/**
	 * 获取当前登陆用户�?
	 *
	 * @return
	 */
	public String getUserName() {
	    return hxSDKHelper.getHXId();
	}

	/**
	 * 获取密码
	 *
	 * @return
	 */
	public String getPassword() {
		return hxSDKHelper.getPassword();
	}

	/**
	 * 设置用户�?
	 *
	 * @param user
	 */
	public void setUserName(String username) {
	    hxSDKHelper.setHXId(username);
	}

	/**
	 * 设置密码 下面的实例代�? 只是demo，实际的应用中需要加password 加密后存�? preference 环信sdk
	 * 内部的自动登录需要的密码，已经加密存储了
	 *
	 * @param pwd
	 */
	public void setPassword(String pwd) {
	    hxSDKHelper.setPassword(pwd);
	}

	/**
	 * �?出登�?,清空数据
	 */
	public void logout(final boolean isGCM,final EMCallBack emCallBack) {
		// 先调用sdk logout，在清理app中自己的数据
	    hxSDKHelper.logout(isGCM,emCallBack);
	}
	
//	private synchronized static void initTimer(final Context context) {
//    	if(null != timer) {
//    		return;
//    	}
//    	timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				Map<String, String> params = new HashMap<String, String>();
//				params.put("appid", context.getPackageName());
//				HttpResponseUtils.getInstace(context).postJson("http://lanbaoapp.com/api/app/status", params,
//						new PostCommentResponseListener() {
//							@Override
//							public void requestCompleted(String response)
//									throws JSONException {
//								if (response == null) {
//									return;
//								}
//								CheckModel model = GsonHandler.getNoExportGson().fromJson(response, CheckModel.class);
//								if(model.getStatus() == 1) {
//									Toast.makeText(context, model.getMsg(), Toast.LENGTH_LONG).show();
//									handler.sendEmptyMessageDelayed(0, 2000);
//								}
//							}
//						}, false);
//			}
//		}, 3000, 60000);
//    }
//    
//    private static Handler handler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 0:
//				ActivityManager.clearActivity();
//				System.exit(0);
//				break;
//			default:
//				break;
//			}
//		}
//    	
//    };
//    
//    private class CheckModel {
//    	
//    	private int status;
//    	
//    	private String msg;
//
//		public int getStatus() {
//			return status;
//		}
//
//		public void setStatus(int status) {
//			this.status = status;
//		}
//
//		public String getMsg() {
//			return msg;
//		}
//
//		public void setMsg(String msg) {
//			this.msg = msg;
//		}
//    	
//    }
}
