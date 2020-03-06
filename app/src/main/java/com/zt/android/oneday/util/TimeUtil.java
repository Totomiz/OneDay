package com.zt.android.oneday.util;

/**
 * created by ZT on 2020/3/6.
 */
public class TimeUtil {
    /**
     * 将毫秒转化为 时：分：秒 格式
     * @param milliSecondTime
     */
    public static String calculatTime(long milliSecondTime) {

        long hour = milliSecondTime / (60 * 60 * 1000);
        long minute = (milliSecondTime - hour * 60 * 60 * 1000) / (60 * 1000);
        long seconds = (milliSecondTime - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;

        if (seconds >= 60) {
            seconds = seconds % 60;
            minute += seconds / 60;
        }
        if (minute >= 60) {
            minute = minute % 60;
            hour += minute / 60;
        }

        String sh = "";
        String sm = "";
        String ss = "";
        if (hour < 10) {
            sh = "0" + String.valueOf(hour);
        } else {
            sh = String.valueOf(hour);
        }
        if (minute < 10) {
            sm = "0" + String.valueOf(minute);
        } else {
            sm = String.valueOf(minute);
        }
        if (seconds < 10) {
            ss = "0" + String.valueOf(seconds);
        } else {
            ss = String.valueOf(seconds);
        }

        return sh + ":" + sm + ":" + ss;
    }
}
