package cn.panghu.blog.common.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/16 13:09
 */
public class DateUtils {

    public static final int FLAG_START = 0;// 当天的开始时间
    public static final int FLAG_END = 1;// 当天的结束时间
    public static final int FLAG_OTHER = 2;// 当天中午时间

    public static final int MORNING = 1;// 上午
    public static final int AFTERNOON = 2;// 下午

    public static final int NO_START = 0;// 未开始
    public static final int IN_PROGRESS = 1; // 进行中
    public static final int IS_OVER = 2; // 已结束
    public static final int IS_CLOSED = 3; // 已关闭
    
    public static final Integer DAY_MONDAY = 1;
    public static final Integer DAY_TUESDAY = 2;
    public static final Integer DAY_WEDNESDAY = 3;
    public static final Integer DAY_THURSDAY = 4;
    public static final Integer DAY_FRIDAY = 5;
    public static final Integer DAY_SATURDAY = 6;
    public static final Integer DAY_SUNDAY = 7;
    public static final Integer DAY_NEXT_MONDAY = 8;

    public static Long remainingTime(Date date){
    	return ChronoUnit.MINUTES.between(Instant.now(), date.toInstant());
    }
    
    public static Date getBeforeByHourTime(Integer ihour){
    	Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
		return calendar.getTime();
    }
    
    /**
     * 获取当天的0点与23.59.59
     *
     * @param flag 0-》起始时间
     *             1-》结束时间
     *             2-》中午时间
     * @return
     */
    public static Date formatInitDay(Date date, Integer flag) {
        Date[] result = new Date[3];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (flag.equals(FLAG_START)) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            result[FLAG_START] = calendar.getTime();
        } else if (flag.equals(FLAG_END)) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 59);
            result[FLAG_END] = calendar.getTime();
        } else if (flag.equals(FLAG_OTHER)) {
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            calendar.set(Calendar.MILLISECOND, 0);
            result[FLAG_OTHER] = calendar.getTime();
        } else {
            return null;
        }
        return result[flag];
    }
    
    /**
     * 根据指定时间获取当前月份
     * @param date
     * @return
     */
    public static Integer getMonthOfNow(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 根据指定时间获取当前年份
     * @param date
     * @return
     */
    public static Integer getYearOfNow(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }
    
    /**
     * 根据指定年  月  获取首日时间
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);  
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return formatInitDay(cal.getTime(), 0);
    }   

    /**
     * 得到本周周一
     *
     * @param date 传入时间
     * @param flag 返回[(0=00:00:00) (1=23:59:59) (2=12:00:00)]
     * @return
     */
    public static Date getMondayOfThisWeek(Date date, Integer flag) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return formatInitDay(c.getTime(), flag);
    }
    
    /**
     * 得到本周日期
     *
     * @param date 传入时间
     * @param day  星期几[1~7]
     * @param flag 返回[(0=00:00:00) (1=23:59:59) (2=12:00:00)]
     * @return
     */
    public static Date getDayOfThisWeek(Date date, Integer day, Integer flag) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + day);
        return formatInitDay(c.getTime(), flag);
    }

    /**
     * 得到本周周日
     *
     * @param date 传入时间
     * @param flag 返回[(0=00:00:00) (1=23:59:59) (2=12:00:00)]
     * @return
     */
    public static Date getSundayOfThisWeek(Date date, Integer flag) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return formatInitDay(c.getTime(), flag);
    }

    /**
     * 判断是早晨还是下午
     *
     * @param date
     * @return
     */
    public static int judgingTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Integer hours = instance.get(Calendar.HOUR_OF_DAY);
        return hours >= 12 ? AFTERNOON : MORNING;
    }

    /**
     * 根据传入时间判定状态
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getStatus(Date startTime, Date endTime) {
        Date d = new Date();
        if (d.before(startTime)) {
            return NO_START;
        }
        if (d.after(startTime) && d.before(endTime)) {
            return IN_PROGRESS;
        }
        if (d.after(endTime)) {
            return IS_OVER;
        }
        return -1;
    }

    /**
     * 返回当月日历表
     *
     * @param date
     * @return
     */
    public static List<Date> getCalendar(Date date) {
        date = formatInitDay(date, FLAG_START);
        List<Date> result = new LinkedList<>();
        int day = getDaysOfMonth(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        for (int i = 1; i <= day; i++) {
            calendar.set(year, mouth, i);
            result.add(calendar.getTime());
        }
        return result;
    }

    /**
     * 获取当月有多少天
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
