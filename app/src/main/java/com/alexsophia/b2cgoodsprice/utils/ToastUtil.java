package com.alexsophia.b2cgoodsprice.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public final class ToastUtil {
    private static Toast mToast;

    public static Toast show(Context context, String showText, int duration, int gravity, int
            xOffset, int yOffset) {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, showText, duration);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.show();
        return mToast;
    }

    public static Toast show(Context context, @StringRes int showText, int duration, int gravity, int
            xOffset, int yOffset) {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, showText, duration);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.show();
        return mToast;
    }

    public static Toast show(Context context, String showText, int duration, int gravity) {
        return show(context, showText, duration, gravity, 0, 0);
    }

    public static Toast show(Context context, @StringRes int showText, int duration, int gravity) {
        return show(context, showText, duration, gravity, 0, 0);
    }

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
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, showText, duration);
        mToast.show();
        return mToast;
    }

    public static Toast showShort(Context context, String showText) {
        return show(context, showText, Toast.LENGTH_SHORT);
    }

    public static Toast showShort(Context context, @StringRes int showText) {
        return show(context, showText, Toast.LENGTH_SHORT);
    }

    public static Toast showShort(Context context, @StringRes int showText, int gravity) {
        return show(context, showText, Toast.LENGTH_SHORT, gravity);
    }

    public static Toast showShort(Context context, String showText, int gravity) {
        return show(context, showText, Toast.LENGTH_SHORT, gravity);
    }

    public static Toast showLong(Context context, String showText) {
        return show(context, showText, Toast.LENGTH_LONG);
    }

    public static Toast showLong(Context context, @StringRes int showText) {
        return show(context, showText, Toast.LENGTH_LONG);
    }

    public static Toast showLong(Context context, String showText, int gravity) {
        return show(context, showText, Toast.LENGTH_LONG, gravity);
    }
}
