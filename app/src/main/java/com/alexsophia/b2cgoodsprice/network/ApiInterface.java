package com.alexsophia.b2cgoodsprice.network;

import org.jsoup.Jsoup;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * ApiInterface
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public class ApiInterface {
    public static Observable<String> getContent(final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(Jsoup.connect(url).get().body().toString());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
