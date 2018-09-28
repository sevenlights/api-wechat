package com.sevenlight.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zhangweihong on 2018/9/27.
 */
public class DateTimeUtil {
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String strDate = df.format(dateTime);
        return strDate;
    }
}
