package com.alexsophia.b2cgoodsprice.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public final class ToastUtil {
    private static Toast mToast;

    public static Toast show(Context context, String showText, int duration) {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, showText, duration);
        mToast.show();
        return mToast;
    }

    public static Toast show(Context context, @StringRes int showText, int duration) {
        return show(context, context.getString(showText), duration);
    }

    public static Toast showShort(Context context, String showText) {
        return show(context, showText, Toast.LENGTH_SHORT);
    }

    public static Toast showShort(Context context, @StringRes int showText) {
        return show(context, showText, Toast.LENGTH_SHORT);
    }

    public static Toast showLong(Context context, String showText) {
        return show(context, showText, Toast.LENGTH_LONG);
    }

    public static Toast showLong(Context context, @StringRes int showText) {
        return show(context, showText, Toast.LENGTH_LONG);
    }
}
