package com.edgar.filemanager.utils;

/**
 * Created by Edgar on 2018/10/23.
 */
public class Preconditions {

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }
}
