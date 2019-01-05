package com.example.erik.bitcoinconverterblog;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network implements INetwork {

    private final OkHttpClient okHttpClient;
    private final Gson gson;

    public Network(){
        this.okHttpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    @Override
    public Observable<Bitcoin> getData(final String url) {
        return Observable.create(new ObservableOnSubscribe<Bitcoin>() {
            @Override
            public void subscribe(ObservableEmitter<Bitcoin> emitter) throws Exception {
                Request request = new Request.Builder().url(url).build();
                Response response = okHttpClient.newCall(request).execute();
                String json = response.body().string();
                Bitcoin bitcoin = gson.fromJson(json, Bitcoin.class);

                emitter.onNext(bitcoin);
            }
        });
    }
}
