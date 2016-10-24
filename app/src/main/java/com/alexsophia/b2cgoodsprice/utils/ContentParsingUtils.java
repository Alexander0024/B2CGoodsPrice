package com.alexsophia.b2cgoodsprice.utils;

/**
 * ContentParsingUtils
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class ContentParsingUtils {
    private static String MARKER_START = "TShop.Setup(";
    private static String MARKER_END = "})();";

    public static String getJsonString(String content) {
        int index_s = content.indexOf(MARKER_START);
        int index_e = content.indexOf(MARKER_END, index_s);
        if (index_s != -1 && index_e != -1) {
            return content.substring(index_s + MARKER_START.length(), index_e - 4).trim();
        }
        return "";
    }
}
