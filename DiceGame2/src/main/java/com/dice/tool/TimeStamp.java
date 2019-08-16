package com.dice.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class TimeStamp
{
    public static Date getDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }

    public static Date getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }

    public static Date getAllDate()
    {
        Date date = new Date();
        return date;
    }

    public static String getJsonDate()
    {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

}
