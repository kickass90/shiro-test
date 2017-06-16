package com.jet.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/6/16 8:58
 * @Description:使用JDK1,8提供的时间类
 */


public class Date8 {

    public static LocalDateTime getTimeByEpochMilli(Long l){
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(l), zoneId);
    }

    public static Long getTimeByLocalDateTime(LocalDateTime localDateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
        Instant instant = Instant.parse(localDateTime.format(dtf));
        return instant.getEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println(getTimeByEpochMilli(1497575451000L));
    }
}
