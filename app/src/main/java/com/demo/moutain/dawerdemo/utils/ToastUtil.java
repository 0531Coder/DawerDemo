package com.demo.moutain.dawerdemo.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.demo.moutain.dawerdemo.core.Application;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class ToastUtil {

    public static void showLongToast(String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(Application.getApplicationContext, content, Toast.LENGTH_LONG).show();
        }
    }
}
