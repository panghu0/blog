package cn.panghu.blog.common.utils;

/**
 * 将阿拉伯数字转为小写中文数字
 * @author pang hu
 * @date 2020/5/16 13:09
 */
public class IntToSmallChineseNumberUtil {

    public static String intToChineseNumber(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += getChineseNumber(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (getChineseNumber(intInput / 10) + "十");
            sd += intToChineseNumber(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (getChineseNumber(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += intToChineseNumber(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (getChineseNumber(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += intToChineseNumber(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (getChineseNumber(intInput / 10000) + "万");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += intToChineseNumber(intInput % 10000);
        }

        return sd;
    }

    private static String getChineseNumber(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

}
