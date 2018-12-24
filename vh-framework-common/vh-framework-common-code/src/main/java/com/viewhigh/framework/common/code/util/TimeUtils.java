/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月9日
 * 修改日期：2018年5月9日
 */
package com.viewhigh.framework.common.code.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description  时间的util类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月9日
 */
public class TimeUtils {
	
	/**
	 * 格式化date
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date string2Date(String dateStr,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 获取年
	 * @param date
	 * @return
	 */
	public static int getYear() {
		return getYear(new Date());
	}
	
	/**
	 * 获取年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 * @return
	 */
	public static int getMonth() {
		return getMonth(new Date());
	}

	/**
	 * 获取月份
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取 日
	 * @return
	 */
	public static int getDayOfMonth() {
		return getDayOfMonth(new Date());
	}

	/**
	 * 获取 日
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取整点的时间戳 以秒为单位
	 * @param n
	 * @return
	 */
	public static Long getPointTimestampInSeconds(int n){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY)-n);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime().getTime()/1000;
    }
	
	/**
	 * 获取整秒的时间戳 以秒为单位
	 * @param n
	 * @return
	 */
	public static Long getSecondTimestampInSeconds(int n){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MINUTE,ca.get(Calendar.MINUTE)-n);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime().getTime()/1000;
	}
	
	public static boolean isSameDay(Date date1, Date date2){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		int year1 = ca.get(Calendar.YEAR);
		int month1 = ca.get(Calendar.MONTH);
		int day1 = ca.get(Calendar.DAY_OF_MONTH);
		ca.setTime(date2);
		int year2 = ca.get(Calendar.YEAR);
		int month2 = ca.get(Calendar.MONTH);
		int day2 = ca.get(Calendar.DAY_OF_MONTH);
		
		return year1==year2 && month1 == month2 && day1 == day2;
	}
	
}
