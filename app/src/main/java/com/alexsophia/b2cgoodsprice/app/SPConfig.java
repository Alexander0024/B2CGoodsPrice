package com.alexsophia.b2cgoodsprice.app;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.utils.SharedPreferencesUtil;

/**
 * SPConfig
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class SPConfig extends SharedPreferencesUtil {
    /**
     * 当前SharedPreference版本
     */
    private final static int CUR_SP_VERSION = 1;
    /**
     * 当前名称
     */
    private final static String NAME = "GOODS_PRICE_CONFIG";

    public SPConfig(Context context) {
        super(context, NAME);
    }
}
