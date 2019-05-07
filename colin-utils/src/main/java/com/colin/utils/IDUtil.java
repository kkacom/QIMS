package com.colin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhilian on 2018/3/30.
 */
public class IDUtil {
    public static int generateId(int len){
        long min = (long) Math.pow(10,len);
        String temp =""+ Math.round(Math.random()*(Integer.MAX_VALUE-min)+min);
        return Integer.valueOf(temp.substring(0,len));
    }

    public static int generateId(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append((int)(Math.random()*9)+1);
        }
        return  Integer.valueOf(sb.toString());
    }
    private static long tmpID = 0;

    private static boolean tmpIDlocked = false;

    public static long getId()
    {
        long ltime = 0;
        while (true)
        {
            if(tmpIDlocked == false)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString()) * 10000;
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}
