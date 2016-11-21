package com.shang.shoppingdemo.bean;

public class Day {

	private int day;
	
	private int whichMonth;  // -1 为上月  1为下月  0为此月
	
	public Day(int day, int whichMonth) {
		this.day = day;
		this.whichMonth = whichMonth;
	}
	
	public Day(int day) { // 上个月 默认为
		this(day, 0);
	}

	public int getDay() {
		return day;
	}

	public int getWhichMonth() {
		return whichMonth;
	}
	
}
