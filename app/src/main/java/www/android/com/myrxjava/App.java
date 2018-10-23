package www.android.com.myrxjava;

import android.app.Application;

import www.android.com.myrxjava.retrofit.RetrofitUtils;

/**
 * Created by sunjays on 2018/10/22.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitUtils retrofitUtils=new  RetrofitUtils();

    }
}
