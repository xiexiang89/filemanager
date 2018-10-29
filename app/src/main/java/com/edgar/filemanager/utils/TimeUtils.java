package com.edgar.filemanager.utils;

/**
 * Created by Edgar on 2018/10/29.
 */
public class TimeUtils {

    public static String formatMilliSecond(long millisecond) {
        int second = (int) (millisecond/1000);
        int hours = (second / 3600);
        int minute = (second % 3600 / 60);
        int s = (second % 60);
        return String.format("%02d:%02d:%02d", hours, minute, s);
    }
}