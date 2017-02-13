package br.com.thiengo.blogapp.presenter;

import android.app.Application;
import android.os.Build;

import com.facebook.accountkit.AccountKit;

/**
 * Created by viniciusthiengo on 12/02/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
    }
}
