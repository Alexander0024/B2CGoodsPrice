package com.alexsophia.b2cgoodsprice.network;

import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.alexsophia.b2cgoodsprice.utils.ContentParsingUtils.getJsonString;

/**
 * JsoupConnect
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class JsoupConnect {
    private static String TAG = "JsoupConnect";

    public static void getContent(final TextView tv, String url) {
        ApiInterface.getContent(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String content) {
                        LogWrapper.e(TAG, "Finished call: " + content);
                        tv.setText(getJsonString(content));
//                        tv.setText(getJsonString(content));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogWrapper.e(TAG, "Error call: " + throwable.getMessage());
                    }
                });
    }
}
