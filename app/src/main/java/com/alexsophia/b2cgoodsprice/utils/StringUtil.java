package com.alexsophia.b2cgoodsprice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串帮助类
 *
 * @author liuweiping
 */
@SuppressWarnings("unused")
public final class StringUtil {
    // 特殊字符替代符
    public static final String REPLACE_STR = "";
    private static String[] chn_arr = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static String ten = "十";
    private static String hundred = "百";
    private static String thousand = "千";
    private static String comma = "、";

    /**
     * 找出特定字符在某个字符串中出现的所有位置的集合
     */
    public static List<Integer> getStrPosition(String str, String op) {
        List<Integer> pos = null;
        if (str != null && op != null) {
            if (!str.equals("") && !op.equals("")) {
                pos = new ArrayList<>();
                int start = 0;
                while (start != str.length()) {
                    int i = str.indexOf(op, start);
                    if (i >= 0) {
                        start = i + 1;
                        pos.add(i);
                    } else
                        break;
                }
            }
        }
        return pos;
    }

    /**
     * 判断一个字符串是否全是数字
     */
    public static boolean isAllNumber(String str) {
        return match(str, "^\\d+$");
    }

    /**
     * 是否全为字母
     */
    public static boolean isEnglishChar(String str) {
        return match(str, "^[a-zA-Z]+$");
    }

    /**
     * 是否为汉字
     */
    public static boolean isChineseCharacters(String str) {
        return match(str, "^[\u4e00-\u9fa5]+$");
    }

    /**
     * 判断一个字符串是否为数字和字母
     */
    public static boolean isNumberOrChar(String str) {
        return match(str, "^[0-9a-zA-Z]+$");
    }

    /**
     * 判断一个字符串是否为邮箱地址
     */
    public static boolean isEmail(String str) {
        return match(str, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    /**
     * 是否为手机号码
     */
    public static boolean isPhoneNumber(String str) {
        return match(str, "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
    }

    /**
     * 是否为6-16位字符串、空格或者英文标点
     */
    public static boolean isPassword(String str) {
        return match(str, "[\\w\\s~`!@#$%^&*( )_+{ }|:”< >?/.,’;= -]{6,16}") &&
                isNotContainsChinese(str);
    }

    /**
     * 隐藏手机号码中的中间4位
     *
     * @param phone 11位手机号码
     * @return 137****6789
     */
    public static String hidePhone(String phone) {
        if (isEmpty(phone)) {
            return "";
        } else if (phone.length() == 11) {
            return phone.substring(0, 3) + "****" + phone.substring(7);
        } else {
            return phone;
        }
    }

//	/**
//	 * 将特殊的符号变成*
//	 * @param source
//	 * @return
//	 */
//	public static String filterEmoji(String source) {
//		if (source != null && !"".equals(source)) {
//			return source.replaceAll(
//				"[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", REPLACE_STR);
//		} else {
//			return source;
//		}
//	}

    /**
     * 隐藏邮箱中间的位数
     *
     * @param mail 邮箱地址
     * @return 隐藏邮箱地址
     */
    public static String hideEmail(String mail) {
        StringBuilder stringBuilder = new StringBuilder();
        if (mail.lastIndexOf("@") > 0) {
            int index = mail.lastIndexOf("@");
            if (index == 1) {
                stringBuilder.append(mail.substring(0, 1));
                stringBuilder.append("***");
                stringBuilder.append(mail.substring(index));
            } else {
                stringBuilder.append(mail.substring(0, 1));
                stringBuilder.append("***");
                stringBuilder.append(mail.substring(index - 1));
            }
        } else {
            return mail;
        }
        String mailHidden = stringBuilder.toString();
        if (mailHidden.length() > 20) {
            return mailHidden.substring(0, 20) + "...";
        } else {
            return mailHidden;
        }
    }

    /**
     * 过滤输入中的中文字符
     *
     * @param str 输入的string字符串
     * @return true：if not contains chinese code; false otherwise.
     */
    private static boolean isNotContainsChinese(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * \w：用于匹配字母，数字或下划线字符
     * \d：用于匹配从0到9的数字；
     * \/ ：表示字符"/"。
     * "^"开头
     * "$"结尾
     * "*"，"+"和"?"这三个符号，表示一个或一序列字符重复出现的次数。它们分别表示“没有或
     * 更多”，“一次或更多”还有“没有或一次”。
     */
    private static boolean match(String str, String rex) {
        if (null == str || str.trim().length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile(rex);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 过滤输入key中的非法字符：清除掉所有特殊字符
     *
     * @param key 用户输入的原始key
     * @return 过滤后的字符串
     */
    public static String stringFilter(String key) {
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(key);
        return m.replaceAll("").trim();
    }

    /**
     * 判断输入内容中是否包含Emoji符号
     *
     * @param source 输入的内容
     * @return true：包含Emjoi；false：不包含Emoji
     */
    public static boolean isContainsEmoji(String source) {
        if (isEmpty(source)) {
            return false;
        }
        int length = source.length();
        for (int i = 0; i < length; i++) {
            char code = source.charAt(i);
            if (isEmoji(code)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmoji(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 判断str中有几个substring
     */
    public static int substringCount(String str, String substring) {
        if (str.contains(substring)) {
            String strReplaced = str.replace(substring, "");
            return (str.length() - strReplaced.length()) / substring.length();
        }
        return 0;
    }

    /**
     * 输入的char是否为中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /*
     * 格式化包含图片的文本(带前缀),如: prefix: http://pre
     * {0}，{1}的dfs@{/upload/79923.jpg,/uplo/4924.jpg,} ==>> <img
     * src="http://pre/upload/79923.jpg">，<img
     * src="http://pre/uplo/4924.jpg">的dfs
     */
    public static String formatImgText(String domain, String text) {
        if (text == null || "".equals(text)) {
            return "";
        }
        String[] separate = text.split("@\\{");
        if (separate.length != 2 || separate[1].length() < 2
                || separate[1].charAt(separate[1].length() - 1) != '}') {
            return formatText(text, domain);
        }
        separate[1] = separate[1].substring(0, separate[1].length() - 1);
        String[] imgArr = separate[1].split(",");
        if (imgArr.length < 0) {
            return formatText(text, domain);
        }
        for (int i = 0; i < imgArr.length; i++) {
            separate[0] = separate[0].replace("{" + i + "}", "<img src =\\\""
                    + imgArr[i] + "\\\">");
        }
        return formatText(separate[0], domain);
    }

    /*
     * 格式化包含图片的文本(带前缀),如: prefix: http://pre
     * {0}，{1}的dfs@{/upload/79923.jpg,/uplo/4924.jpg,} ==>> <img
     * src="http://pre/upload/79923.jpg">，<img
     * src="http://pre/uplo/4924.jpg">的dfs
     */
    public static String formatImgText1(String prefix, String text) {
        if (text == null || "".equals(text))
            return text;
        String[] separate = text.split("@\\{");
        if (separate.length != 2 || separate[1].length() < 2
                || separate[1].charAt(separate[1].length() - 1) != '}')
            return text;

        separate[1] = separate[1].substring(0, separate[1].length() - 1);
        String[] imgArr = separate[1].split(",");
        if (imgArr.length < 0)
            return text;

        LogWrapper.e("StringUtil", "dxf: " + separate[1] + "; arr: " + imgArr.length);
        for (int i = 0; i < imgArr.length; i++) {
            separate[0] = separate[0].replace("{" + i + "}", "<img src =\""
                    + prefix + imgArr[i] + "\">");
        }
        return separate[0];
    }

    /**
     * 转义单引号，/</n><//n>
     */
    private static String formatText(String text, String domain) {
        if (text == null || "".equals(text)) {
            return text;
        }
        text = text.replaceAll("\"", "\'").replaceAll("\t", " ").replaceAll("\r", "<br>")
                .replaceAll("/upload", domain + "/upload");
        return text;
    }

    /**
     * 获取网络下载地址 由domain 和path 组成
     * 如果是以http://开头，则直接返回；否则用domain和path拼接而成
     */
    public static String getImageNetPath(String path, String domain) {
        if (path.startsWith("http://")) {
            return path;
        } else {
            if (domain.endsWith("/") && path.startsWith("/")) {
                return domain + path.substring(1);
            } else if (!domain.endsWith("/") && path.startsWith("/")) {
                return domain + path;
            } else if (domain.endsWith("/") && !path.startsWith("/")) {
                return domain + path;
            } else {
                return domain + "/" + path;
            }
        }
    }

    /**
     * 格式化 电子化移交完成率 保留两位
     */
    public static String formatRate(String rateStr) {
        if (rateStr.contains(".")) {
            //获取小数点的位置
            int num = rateStr.indexOf(".");
            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");
            if (afterData.length() < 2) {
                afterData = afterData + "0";
            }
            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            if ("1".equals(rateStr)) {
                return "100";
            } else {
                return rateStr;
            }
        }
    }

    /**
     * 根据传入的数字，获取对应的中文序号表示，有效范围 1 ~ 9999，可添加context从String取值。
     */
    public static String getChinese(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int current = 0;
        int length = String.valueOf(num).length();
        boolean flag = false; // 连续拼零的标志位
        boolean needBreak = false; // 是否继续的标志位
        switch (length) {
            case 4:
                // 获取千位的数字
                current = num / 1000;
                // 拼接第四位
                stringBuilder.append(chn_arr[current]).append(thousand);
            case 3:
                // 更新剩余数字
                num -= current * 1000;
                if (num == 0) {
                    // 如果剩余为0
                    break;
                }
                if (num % 100 == 0) {
                    needBreak = true;
                }
                // 获取百位的数字
                current = num / 100;
                // 如果为零，拼“零”，不为零则拼数字
                if (current != 0) {
                    stringBuilder.append(chn_arr[current]).append(hundred);
                    flag = false;
                } else {
                    stringBuilder.append(chn_arr[0]);
                    flag = true;
                }
                if (needBreak) {
                    break;
                }
            case 2:
                // 更新剩余数字
                num -= current * 100;
                if (num == 0) {
                    // 如果剩余为0
                    break;
                }
                if (num % 10 == 0) {
                    needBreak = true;
                }
                // 获取十位数字
                current = num / 10;
                // 如果为零，拼“零”，不为零则拼数字
                if (length == 2 && current == 1) {
                    stringBuilder.append(ten);
                } else if (current != 0) {
                    stringBuilder.append(chn_arr[current]).append(ten);
                } else if (!flag) {
                    stringBuilder.append(chn_arr[0]);
                }
                if (needBreak) {
                    break;
                }
            case 1:
                num -= current * 10;
                current = num;
                if (num != 0) {
                    stringBuilder.append(chn_arr[current]).append(comma);
                }
        }
        return stringBuilder.toString();
    }
}
