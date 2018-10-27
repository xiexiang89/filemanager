package com.edgar.filemanager.utils;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.filemanager.R;
import com.facebook.drawee.view.SimpleDraweeView;

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

    public static void setImageUrl(View view, @IdRes int id, Uri uri) {
        SimpleDraweeView imageView = view.findViewById(id);
        imageView.setImageURI(uri);
    }
}
