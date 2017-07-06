package com.example.hui.adapter.xlistview;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * ============================================================
 * 
 * copyright ：好莱信息科技有限公司 (c) 2014
 * 
 * author : HUI
 * 
 * version ：1.1
 * 
 * date created ： On November 24, 2014 9:30:34
 * 
 * description ：Date tools 1. Get a date format 2.Get the current date in a
 * certain format
 * 
 * revision history ： On January, 2014 1:22:05
 * 
 * ============================================================
 * 
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {

	private static SimpleDateFormat dataFormart;

	public static SimpleDateFormat getSimpleDateFormat(String DATE_PATTERN) {
		dataFormart = new SimpleDateFormat(DATE_PATTERN);
		return dataFormart;
	}

	public static String getCurrentTime(String DATE_PATTERN) {
		dataFormart = new SimpleDateFormat(DATE_PATTERN);
		return dataFormart.format(new Date());
	}

	/**
	 * Will (on day month year) -- > (year - month - day) date format
	 */
	public static String dateTransform(String dateData) {
		String year = dateData.substring(0, dateData.indexOf("年"));
		String month = dateData.substring(dateData.indexOf("年") + 1,
				dateData.indexOf("月"));
		String day = dateData.substring(dateData.indexOf("月") + 1,
				dateData.indexOf("日"));
		return year + "-" + month + "-" + day;
	}

	public static String getAssignTime(String DATE_PATTERN, long time) {
		dataFormart = new SimpleDateFormat(DATE_PATTERN);
		return dataFormart.format(time);
	}

	/**
	 * Formatting a long time value (the current time)
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentTimeText(final long systemCurrentTime) {
		if (systemCurrentTime == 0) {
			return "N之前更新";
		}
		return getCurrentTime("yyyy-MM-dd HH:mm");
	}

	public static long TimeStrToLong(String timeStr, String timeStrFormat) {
		// 处理小时
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeStrFormat);
		Date date = null;
		try {
			date = simpleDateFormat.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return date.getTime() / 1000;
	}

	/**
	 * 得到当前日期
	 * @return
	 */
	public static ItemDate getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		return new ItemDate(year, month, day);
	}
	
	/**
	 * 自定义的一个日期类
	 */
	public static class ItemDate {
		private int year;
		private int month;
		private int day;

		public ItemDate() {
		}

		public ItemDate(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public String toString() {
			return this.year + "年" + this.month + "月" + this.day + "日";
		}

		public boolean equals(ItemDate itemDate) {
			return this.year == itemDate.getYear()
					&& this.month == itemDate.getMonth()
					&& this.day == itemDate.getDay();
		}
	}
	
	/**
	 * 根据当前年月获取天数
	 */
	public static int getDaysByMonth(int year,int month){
		int[] monDays = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if ( ( (year) % 4 == 0 && (year) % 100 != 0) ||(year) % 400 == 0) 
		        monDays[2]++;
		return monDays[month];
	}
}
