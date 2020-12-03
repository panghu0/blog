package cn.panghu.blog.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author pang hu
 * @date 2020/5/16 13:09
 */
public class DateFormateUtils {

    /** The ymdhmsformatter. */
    public static DateTimeFormatter ymdhmsformatter = DateTimeFormat.forPattern("yyMMddhhmmss");

    /** The y_m_dhmsformatter. */
    public static DateTimeFormatter y_m_dhmsformatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    /** The y_m_d formatter. */
    public static DateTimeFormatter y_m_dFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    /** The ymd formatter. */
    public static DateTimeFormatter ymdFormatter = DateTimeFormat.forPattern("yyyyMMdd");
    /** The y_m_dhm formatter*/
    public static DateTimeFormatter y_m_dhmFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");


    public static Date toDate(String dateStr, DateTimeFormatter format){
        if(format == null || StringUtils.isBlank(dateStr)){
            return null;
        }
        return format.parseDateTime(dateStr).toDate();
    }

    public static String toString(Date date, DateTimeFormatter format){
        if(date == null || format == null){
            return null;
        }
        return format.print(date.getTime());
    }

    /**
     * To ymdhms.
     *
     * @param timeMills the time mills
     * @return the string
     */
    public static String toYMDHMS(long timeMills) {
        return ymdhmsformatter.print(timeMills);
    }

    /**
     * To ymd.
     *
     * @param timeMills the time mills
     * @return the string
     */
    public static String toYMD(long timeMills) {
        return ymdFormatter.print(timeMills);
    }

    /**
     * To y_ m_ d.
     *
     * @param timeMills the time mills
     * @return the string
     */
    public static String toY_M_D(long timeMills) {
        return y_m_dFormatter.print(timeMills);
    }

    /**
     * To y_ m_ dhms.
     *
     * @param timeMills the time mills
     * @return the string
     */
    public static String toY_M_DHMS(long timeMills) {
        return y_m_dhmsformatter.print(timeMills);
    }

    /**
     * To y_ m_ dhm
     *
     * @param timeMills the time mills
     * @return the string
     */
    public static String toY_M_DHM(long timeMills){
        return y_m_dhmFormatter.print(timeMills);
    }
}
