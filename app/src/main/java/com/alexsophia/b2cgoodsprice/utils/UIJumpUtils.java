package com.alexsophia.b2cgoodsprice.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/9/21.
 */
public class UIJumpUtils {

    /**
     * 使用浏览器打开网页
     */
    static public boolean openWebpageOnBrowser(@NonNull Context context, @Nullable String url) {
        if (StringUtil.isEmpty(url))
            return false;

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        return startBrowserActivity(context, url);
    }

    private static boolean startBrowserActivity(@NonNull Context context, @NonNull String url) {
        try {
            Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
