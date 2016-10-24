package com.alexsophia.b2cgoodsprice.utils;

import android.util.Log;

import com.alexsophia.b2cgoodsprice.BuildConfig;

/**
 * Debug 模式 或 open 状态下输出log
 */
@SuppressWarnings("unused")
public final class LogWrapper {
	private static boolean DEBUG = BuildConfig.DEBUG;
	private static boolean OPEN = false;

	public static void openLog(boolean open) {
		OPEN = open;
	}

	public static boolean isOpenLog() {
		return OPEN;
	}

	/** Print log in info level */
	public static void i(String tag, String msg) {
		if (DEBUG || OPEN)
			Log.i(tag, msg + "");
	}

	/** Print log in debug level */
	public static void d(String tag, String msg) {
		if (DEBUG || OPEN)
			Log.d(tag, msg + "");
	}

	/** Print log in error level */
	public static void e(String tag, String msg) {
		if (DEBUG || OPEN)
			Log.e(tag, msg + "");
	}

	/** Print log in warning level */
	public static void w(String tag, String msg) {
		if (DEBUG || OPEN)
			Log.w(tag, msg + "");
	}

	/** Print log in verbose level */
	public static void v(String tag, String msg) {
		if (DEBUG || OPEN)
			Log.v(tag, msg + "");
	}
}
