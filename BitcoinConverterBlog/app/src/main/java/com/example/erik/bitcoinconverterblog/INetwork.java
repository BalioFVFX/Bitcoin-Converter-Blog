package com.example.erik.bitcoinconverterblog;

import io.reactivex.Observable;
public interface INetwork {
    Observable<Bitcoin> getData(final String url);
}

