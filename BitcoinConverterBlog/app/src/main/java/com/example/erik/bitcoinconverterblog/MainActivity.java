package com.example.erik.bitcoinconverterblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String url = "https://api.cryptonator.com/api/ticker/btc-usd";
    private INetwork network;
    private Button refreshButton;
    private TextView btcTextView;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.refreshButton = findViewById(R.id.btn_refresh);
        this.btcTextView = findViewById(R.id.tv_btc);
        this.setButtonListener();

        this.network = new Network();
        this.compositeDisposable = new CompositeDisposable();
    }

    void setButtonListener() {
        this.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compositeDisposable.add(network.getData(url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Bitcoin>() {
                            @Override
                            public void accept(Bitcoin bitcoin) throws Exception {
                                btcTextView.setText(String.format("1 BTC equals %s USD", bitcoin.getTicker().getPrice()));
                            }
                        }));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.compositeDisposable.clear();
    }
}


