package com.edgar.filemanager.utils;

import android.view.View;
import android.widget.TextView;

import com.edgar.filemanager.R;

import androidx.annotation.IdRes;

/**
 * Created by Edgar on 2018/10/26.
 */
public class ViewUtils {

    private ViewUtils() {}

    public static void setText(View view, @IdRes int id, CharSequence text) {
        TextView textView = view.findViewById(id);
        textView.setText(text);
    }
}
