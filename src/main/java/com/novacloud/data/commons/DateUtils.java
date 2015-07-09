package com.novacloud.data.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: 系统时间公共类
 * </p>
 * <li>提供取得系统时间的所有共用方法</li>
 */
public class DateUtils {
	private static final String timeRegex = "\\d{1,2}[:时]\\d{1,2}([:分]\\d{1,2})?";
	private static final Pattern timePattern = Pattern.compile(timeRegex,
			Pattern.MULTILINE);

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（Date）
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 取得昨天此时的时间
	 * 
	 * @return 昨天日期（Date）
	 */
	public static Date getYesterdayDate() {
		return new Date(getCurTimeMillis() - 0x5265c00L);
	}

	/**
	 * 取得去过i天的时间
	 * 
	 * @param i
	 *            过去时间天数
	 * @return 昨天日期（Date）
	 */
	public static Date getPastdayDate(int i) {
		return new Date(getCurTimeMillis() - 0x5265c00L * i);
	}

	/**
	 * 取得差距i小时的时间
	 * 
	 * @param i
	 *            小时数(可以正负表示)
	 * @return 日期（Date）
	 */
	public static Date getPastHour(int i) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.add(Calendar.HOUR, i);
		return CALENDAR.getTime();
	}

	/**
	 * 取得差距i分钟的时间
	 * 
	 * @param i
	 *            分钟数(可以正负表示)
	 * @return 日期（Date）
	 */
	public static Date getPastMinite(int i) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.add(Calendar.MINUTE, i);
		return CALENDAR.getTime();
	}

	/**
	 * 取得差距i秒的时间
	 * 
	 * @param i
	 *            秒数(可以正负表示)
	 * @return 日期（Date）
	 */
	public static Date getPastSecond(int i) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.add(Calendar.SECOND, i);
		return CALENDAR.getTime();
	}

	/**
	 * 取得过去某时间过去i天的时间
	 * 
	 * @param i
	 *            过去时间天数
	 * @return 过去日期（Date）
	 */
	public static Date getPastdayDate(Date date, int i) {
		return new Date(date.getTime() - 0x5265c00L * i);
	}

	/**
	 * 获取时间差
	 * 
	 * @param amount
	 *            时间差(可以正负表示)
	 * @param type
	 *            类型
	 * @return
	 */
	public static Date getDatePast(int amount, int type) {
		Calendar c = Calendar.getInstance();
		c.add(type, amount);
		return c.getTime();
	}

	/**
	 * 取得当前时间的长整型表示
	 * 
	 * @return 当前时间（long）
	 */
	public static long getCurTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 取得当前时间的特定表示格式的字符串
	 * 
	 * @param formatDate
	 *            时间格式（如：yyyy/MM/dd hh:mm:ss）
	 * @return 当前时间
	 */
	public static synchronized String getCurFormatDate(String formatDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		Date date = getCurrentDate();
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得某日期时间的特定表示格式的字符串
	 * 
	 * @param format
	 *            时间格式
	 * @param date
	 *            某日期（Date）
	 * @return 某日期的字符串
	 */
	public static synchronized String getDate2Str(String format, Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将日期转换为长字符串（包含：年-月-日 时:分:秒）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd HH:mm:ss 的字符串
	 */
	public static String getDate2LStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 将日期转换为长字符串（包含：年/月/日 时:分:秒）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd HH:mm:ss 的字符串
	 */
	public static String getDate2LStr2(Date date) {
		return getDate2Str("yyyy/MM/dd HH:mm:ss", date);
	}

	/**
	 * 将日期转换为中长字符串（包含：年-月-日 时:分）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd HH:mm 的字符串
	 */
	public static String getDate2MStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm", date);
	}

	/**
	 * 将日期转换为中长字符串（包含：年/月/日 时:分）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd HH:mm 的字符串
	 */
	public static String getDate2MStr2(Date date) {
		return getDate2Str("yyyy/MM/dd HH:mm", date);
	}

	/**
	 * 将日期转换为短字符串（包含：年-月-日）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd 的字符串
	 */
	public static String getDate2SStr(Date date) {
		return getDate2Str("yyyy-MM-dd", date);
	}

	/**
	 * 将日期转换为短字符串（包含：年/月/日）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd 的字符串
	 */
	public static String getDate2SStr2(Date date) {
		return getDate2Str("yyyy/MM/dd", date);
	}

	/**
	 * 取得型如：yyyyMMddhhmmss的字符串
	 * 
	 * @param date
	 * @return 返回型如：yyyyMMddhhmmss 的字符串
	 */
	public static String getDate2All(Date date) {
		return getDate2Str("yyyyMMddhhmmss", date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd HH:mm:ss的长字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @return 日期型的字符串
	 */
	public static String getLong2LStr(long l) {
		Date date = getLongToDate(l);
		return getDate2LStr(date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd的长字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @return 日期型的字符串
	 */
	public static String getLong2SStr(long l) {
		Date date = getLongToDate(l);
		return getDate2SStr(date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换指定格式的字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @param formatDate
	 *            指定的日期格式
	 * @return 日期型的字符串
	 */
	public static String getLong2SStr(long l, String formatDate) {
		Date date = getLongToDate(l);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	private static synchronized Date getStrToDate(String format, String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(format);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将某指定的字符串转换为某类型的字符串
	 * 
	 * @param format
	 *            转换格式
	 * @param str
	 *            需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String getStr2Str(String format, String str) {
		Date tempDate = getStrToDate(format, str);
		return getDate2Str(format, tempDate);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2LDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm:ss", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm:ss的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2LDate2(String str) {
		return getStrToDate("yyyy/MM/dd HH:mm:ss", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2MDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2MDate2(String str) {
		return getStrToDate("yyyy/MM/dd HH:mm", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2SDate(String str) {
		return getStrToDate("yyyy-MM-dd", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2SDate2(String str) {
		return getStrToDate("yyyy/MM/dd", str);
	}

	/**
	 * 将某长整型数据转换为日期
	 * 
	 * @param l
	 *            长整型数据
	 * @return 转换后的日期
	 */
	public static Date getLongToDate(long l) {
		return new Date(l);
	}

	/**
	 * 以分钟的形式表示某长整型数据表示的时间到当前时间的间隔
	 * 
	 * @param l
	 *            长整型数据
	 * @return 相隔的分钟数
	 */
	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurTimeMillis());
	}

	/**
	 * 以分钟的形式表示两个长整型数表示的时间间隔
	 * 
	 * @param from
	 *            开始的长整型数据
	 * @param to
	 *            结束的长整型数据
	 * @return 相隔的分钟数
	 */
	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	/**
	 * 以天的形式表示两个长整型数表示的时间间隔
	 * 
	 * @param from
	 *            开始的长整型数据
	 * @param to
	 *            结束的长整型数据
	 * @return 相隔的天数
	 */
	public static int getOffDays(long from, long to) {
		return (int) ((to - from) / 86400000L);
	}

	/**
	 * 以微秒的形式赋值给一个Calendar实例
	 * 
	 * @param l
	 *            用来表示时间的长整型数据
	 */
	public static void setCalendar(long l) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.clear();
		CALENDAR.setTimeInMillis(l);
	}

	/**
	 * 以日期的形式赋值给某Calendar
	 * 
	 * @param date
	 *            指定日期
	 */
	public static void setCalendar(Date date) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.clear();
		CALENDAR.setTime(date);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某年
	 */
	public static int getYear() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(1);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某月
	 */
	public static int getMonth() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(2) + 1;
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某天
	 */
	public static int getDay() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(5);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某小时
	 */
	public static int getHour() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(11);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某分钟
	 */
	public static int getMinute() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(12);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某秒
	 */
	public static int getSecond() {
		Calendar CALENDAR = Calendar.getInstance();
		return CALENDAR.get(13);
	}

	/**
	 * formate only date string without time
	 * 
	 * @param date
	 * @return
	 */
	private static Calendar formatDate(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		try {
			String[] tmp = date.replaceAll("\\D", " ").split("\\s+");
			ArrayList<Integer> dateFields = new ArrayList<Integer>(3);
			for (int i = 0; i < tmp.length; i++) {
				int dateField = StringUtils.tryParseInt(tmp[i]);
				if (dateField > 0) {
					dateFields.add(dateField);
				}
			}
			if (dateFields.size() > 2) {// considered : year,month and day
				if (dateFields.get(0) > 1000) {
					calendar.set(dateFields.get(0), dateFields.get(1) - 1,
							dateFields.get(2));
				} else {// TODO:not support before 1999 year
					calendar.set(dateFields.get(0) + 2000,
							dateFields.get(1) - 1, dateFields.get(2));
				}
			} else if (dateFields.size() == 2) {// considered : month and day
				calendar.set(Calendar.MONTH, dateFields.get(0) - 1);
				calendar.set(Calendar.DAY_OF_MONTH, dateFields.get(1));
			}
		} catch (Exception e) {
		}
		return calendar;
	}

	private static Calendar formatTime(String time) {
		time = time.replace("时", ":");
		time = time.replace("分", ":");
		time = time.replace("秒", "");
		Calendar calendar = Calendar.getInstance();
		try {
			String[] tmp = time.split(":");
			ArrayList<Integer> dateFields = new ArrayList<Integer>(3);
			for (int i = 0; i < tmp.length; i++) {
				if ("00".equals(tmp[i])) {
					dateFields.add(0);
				} else {
					int dateField = StringUtils.tryParseInt(tmp[i]);
					if (dateField > 0) {
						dateFields.add(dateField);
					}
				}
			}
			calendar.set(Calendar.HOUR_OF_DAY, dateFields.get(0));
			calendar.set(Calendar.MINUTE, dateFields.get(1));
			calendar.set(Calendar.SECOND, 0);
			if (dateFields.size() > 2) {// considered : hour,minute and second
				calendar.set(Calendar.SECOND, dateFields.get(2));
			}
		} catch (Exception e) {
		}
		return calendar;
	}

	/**
	 * 格式化日期
	 * 
	 * @param before
	 *            日期字符串
	 * @return 格式化完毕的日期（为空直接返回当前时间)
	 */
	public static Date formatDateTime(String before) {
		if (StringUtils.isNullOrEmpty(before)) {
			return DateUtils.getPastdayDate(7);
		}
		Calendar calendar = Calendar.getInstance();
		Matcher matcher = timePattern.matcher(before);
		if (matcher.find()) {// have time
			int timeStartPostion = matcher.start();
			String onlyDate = before.substring(0, timeStartPostion);
			calendar = formatDate(onlyDate);
			Calendar timeCalendar = formatTime(matcher.group());
			calendar.set(Calendar.HOUR_OF_DAY,
					timeCalendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

		} else {// only date
			calendar = formatDate(before);

		}
		return calendar.getTime();
	}

	public static void main(String[] args) {
		String date = "12/4/5";
		Date d = DateUtils.formatDateTime(date);
		System.out.println(DateUtils.getDate2LStr(d));
	}
}
