package com.zlt.bean;

public class Users {
	private int id;
	private String txt;
	private int nowpage;//当前页
	private int limitpage;//记录数
	private int howManyPages;//限制每页记录数
	public int getHowManyPages() {

		return howManyPages;
	}

	public void setHowManyPages(int howManyPages) {
		this.howManyPages = howManyPages;
	}

	public String getTxt() {
		return txt;
	}

	public int getId() {
		return id;
	}
	//将地址值转换成字符串
	public String toString() {
		return "ID编号:" + id + ",内容:" + txt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage =nowpage;
	}

	public int getLimitpage() {
		return limitpage;
	}

	public void setLimitpage(int limitpage) {
		this.limitpage = limitpage;
	}
	
	
}
