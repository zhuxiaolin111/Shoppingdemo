package com.shang.shoppingdemo.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.shang.shoppingdemo.bean.Day;
import com.shang.shoppingdemo.constants.CommonConstants;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.MonthDisplayHelper;

@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class CommonUtils {

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static String chineseToPinYin(String chinese) {
		String result = chinese.toUpperCase();
		try {
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

			format.setCaseType(HanyuPinyinCaseType.UPPERCASE);

			format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

			format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

			String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(
					chinese.charAt(0), format);
			if (null != pinyin) {
				result = pinyin[0];
			} else {
				char c = chinese.toUpperCase().charAt(0);
				if (c < 65 || c > 90) {
					result = "#";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getRealPathFromURI(Uri uri, ContentResolver resolver) {
		String result = null;
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = resolver.query(uri, proj, null, null, null);
		if (null != cursor) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			result = cursor.getString(column_index);
			cursor.close();
		} else {
			result = uri.getPath();
		}
		return result;
	}

	public static List<Day> getCalendarDataByDate(Date date) {
		List<Day> days = new ArrayList<Day>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar currenCalendar = Calendar.getInstance();
		currenCalendar.setTime(new Date());
		// 1 为日历以周日开头
		// MonthDisplayHelper mHelper = new
		// MonthDisplayHelper(calendar.get(Calendar.YEAR),
		// calendar.get(Calendar.MONTH), 1);
		// 2 为日历以周一开头
		MonthDisplayHelper mHelper = new MonthDisplayHelper(
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 2);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		boolean isBreak = false;
		for (int i = 0; i < 6; i++) {
			int n[] = mHelper.getDigitsForRow(i);
			for (int d = 0; d < n.length; d++) {
				if (mHelper.isWithinCurrentMonth(i, d)) {
					days.add(new Day(n[d]));
					if (maxDay == n[d]) {
						isBreak = true;
					}
				} else if (i == 0) {
					days.add(new Day(n[d], -1));
				} else {
					days.add(new Day(n[d], 1));
				}

			}
			if (isBreak) {
				break;
			}
		}
		return days;
	}

	public static String convertDate(long date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date(date * 1000));
	}

	public static String getImagePath() {
		File file11 = null;
		file11 = new File(CommonConstants.SDPATH);
		if (!file11.exists()) {
			file11.mkdir();
		}

		File file = new File(file11, String.valueOf(System.currentTimeMillis())
				+ ".jpg");
		return file.getPath();
	}

	/**
	 * 添加联系人 数据一个表一个表的添加，每次都调用insert方法
	 * */
	public static void addContacts(Context context, String name, String mobile, String email) {
		/* 往 raw_contacts 中添加数据，并获取添加的id号 */
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		long contactId = ContentUris.parseId(resolver.insert(uri, values));

		/* 往 data 中添加数据（要根据前面获取的id号） */
		// 添加姓名
		uri = Uri.parse("content://com.android.contacts/data");
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/name");
		values.put("data2", name);
		resolver.insert(uri, values);

		// 添加电话
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		values.put("data2", "2");
		values.put("data1", mobile);
		resolver.insert(uri, values);

		// 添加Email
		values.clear();
		values.put("raw_contact_id", contactId);
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		values.put("data2", "2");
		values.put("data1", email);
		resolver.insert(uri, values);
	}

	/**
	 * 添加联系人 在同一个事务中完成联系人各项数据的添加
	 * 使用ArrayList<ContentProviderOperation>，把每步操作放在它的对象中执行
	 * */
	public static void addContacts2(Context context, String name, String mobile, String email) {
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		// 第一个参数：内容提供者的主机名
		// 第二个参数：要执行的操作
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();

		// 操作1.添加Google账号，这里值为null，表示不添加
		ContentProviderOperation operation = ContentProviderOperation
				.newInsert(uri).withValue("account_name", null)// account_name:Google账号
				.build();

		// 操作2.添加data表中name字段
		uri = Uri.parse("content://com.android.contacts/data");
		ContentProviderOperation operation2 = ContentProviderOperation
				.newInsert(uri)
				// 第二个参数int previousResult:表示上一个操作的位于operations的第0个索引，
				// 所以能够将上一个操作返回的raw_contact_id作为该方法的参数
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/name")
				.withValue("data2", name).build();

		// 操作3.添加data表中phone字段
		uri = Uri.parse("content://com.android.contacts/data");
		ContentProviderOperation operation3 = ContentProviderOperation
				.newInsert(uri).withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2")
				.withValue("data2", "2").withValue("data1", mobile)
				.build();

		// 操作4.添加data表中的Email字段
		uri = Uri.parse("content://com.android.contacts/data");
		ContentProviderOperation operation4 = ContentProviderOperation
				.newInsert(uri).withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/email_v2")
				.withValue("data2", "2")
				.withValue("data1", email).build();

		operations.add(operation);
		operations.add(operation2);
		operations.add(operation3);
		operations.add(operation4);

		try {
			resolver.applyBatch("com.android.contacts", operations);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
