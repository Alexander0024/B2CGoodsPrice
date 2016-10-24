package com.alexsophia.b2cgoodsprice.network;

import com.alexsophia.b2cgoodsprice.utils.ContentParsingUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * JsoupConnect
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class JsoupConnect {

    public static String getContent(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String name = ContentParsingUtils.getJsonString(document.body().data());
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        getContent("http://item.taobao.com/item.htm?id=534149604055");
    }
}
