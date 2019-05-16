package com.zhilian.rf_qims.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018-1-2.
 */
public class DateUtil {
    public static DateUtil instance = null;

    private SimpleDateFormat sdf = null;


    private DateUtil() {

    }

    public static synchronized DateUtil getInstance() {
        if (null == instance) {
            synchronized (DateUtil.class) {
                instance = new DateUtil();
            }
        }
        return instance;
    }


    public Date str2Date(String strDate) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public double diffDays(String date2, String date1) {
        return (int) ((str2Date(date2).getTime() - str2Date(date1).getTime()) / (1000 * 3600 * 24));
    }

    public int[] splitDate(String strDate) {
        String[] arr = strDate.split("-");
        int[] dates = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dates[i] = Integer.valueOf(arr[i].trim());
        }
        return dates;
    }

    public static boolean checkDate(String target, String source) {
        String[] arr1 = target.split("-");
        String[] arr2 = source.split("-");
        int[] temp1 = new int[arr1.length];
        int[] temp2 = new int[arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            String value1 = arr1[i].trim();
            String value2 = arr2[i].trim();
            if (!isBlank(value1)) {
                temp1[i] = Integer.valueOf(value1);
            } else {
                throw new RuntimeException(new NullPointerException("日期格式不正确，请检查"));
            }
            if (!isBlank(value2)) {
                temp2[i] = Integer.valueOf(value2);
            } else {
                throw new RuntimeException(new NullPointerException("日期格式不正确，请检查"));
            }
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(target);
            Date dt2 = df.parse(source);
            if (dt1.after(dt2)) { //表示dt1在dt2后
                throw new RuntimeException(new NullPointerException("结束日期小于开始日期"));
            } else {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static boolean isBlank(String source) {
        return source.equals("") || null == source;
    }
}
