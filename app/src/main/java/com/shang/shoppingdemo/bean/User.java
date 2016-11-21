package com.shang.shoppingdemo.bean;

import java.io.Serializable;

public class User implements Serializable {
	private String uid;  //用户的id
	private String uname;//用户名称
	private String nickname;//用户昵称
	private String avator;//用户头像
	private String mobile; //用户手机叿
	private String password;//用户密码
	private String balance; //用户的账户余颿
	private String score;//用户的积刿
	private String sex;  //用户的濧别(0:未设罿1:甿2:奿)
	private String birthday;//用户生日
	private String gags; //是否能评论（0:昿1:否）
	private String grade;//用户的等线
	private String channel;//用户的渠避
	private String loginmode;//用户的登录平叿(0:平台登录1:qq2:微信3:支付宿)
	private String addtime; //创建时间
	private String status;//用户状濿(0:正常1:冻结)
	private String wxid;// 微信的授权字符串
	private String qqid; // qq的授权字符串
	private String wbid;// 微博的授权字符串
	public User(String uid, String uname, String nickname, String avator,
			String mobile, String password, String balance, String score,
			String sex, String birthday, String gags, String grade,
			String channel, String loginmode, String addtime, String status,
			String wxid, String qqid, String wbid) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.nickname = nickname;
		this.avator = avator;
		this.mobile = mobile;
		this.password = password;
		this.balance = balance;
		this.score = score;
		this.sex = sex;
		this.birthday = birthday;
		this.gags = gags;
		this.grade = grade;
		this.channel = channel;
		this.loginmode = loginmode;
		this.addtime = addtime;
		this.status = status;
		this.wxid = wxid;
		this.qqid = qqid;
		this.wbid = wbid;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the avator
	 */
	public String getAvator() {
		return avator;
	}
	/**
	 * @param avator the avator to set
	 */
	public void setAvator(String avator) {
		this.avator = avator;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the gags
	 */
	public String getGags() {
		return gags;
	}
	/**
	 * @param gags the gags to set
	 */
	public void setGags(String gags) {
		this.gags = gags;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * @return the loginmode
	 */
	public String getLoginmode() {
		return loginmode;
	}
	/**
	 * @param loginmode the loginmode to set
	 */
	public void setLoginmode(String loginmode) {
		this.loginmode = loginmode;
	}
	/**
	 * @return the addtime
	 */
	public String getAddtime() {
		return addtime;
	}
	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the wxid
	 */
	public String getWxid() {
		return wxid;
	}
	/**
	 * @param wxid the wxid to set
	 */
	public void setWxid(String wxid) {
		this.wxid = wxid;
	}
	/**
	 * @return the qqid
	 */
	public String getQqid() {
		return qqid;
	}
	/**
	 * @param qqid the qqid to set
	 */
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}
	/**
	 * @return the wbid
	 */
	public String getWbid() {
		return wbid;
	}
	/**
	 * @param wbid the wbid to set
	 */
	public void setWbid(String wbid) {
		this.wbid = wbid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", nickname="
				+ nickname + ", avator=" + avator + ", mobile=" + mobile
				+ ", password=" + password + ", balance=" + balance
				+ ", score=" + score + ", sex=" + sex + ", birthday="
				+ birthday + ", gags=" + gags + ", grade=" + grade
				+ ", channel=" + channel + ", loginmode=" + loginmode
				+ ", addtime=" + addtime + ", status=" + status + ", wxid="
				+ wxid + ", qqid=" + qqid + ", wbid=" + wbid + "]";
	}
	
    

}
