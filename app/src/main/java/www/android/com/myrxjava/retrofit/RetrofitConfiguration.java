package www.android.com.myrxjava.retrofit;

import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Retrofit的配置联网
 * Created by sunjays on 2018/10/
 */
public class RetrofitConfiguration {

    private static OkHttpClient.Builder builder;

    public RetrofitConfiguration() {
        builder=new OkHttpClient.Builder();
    }


    public OkHttpClient.Builder getBuilder() {

        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OKGO");
        //设定日志级别
        //log打印级别，决定了log显示的详细程度
        httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        httpLoggingInterceptor.setColorLevel(Level.INFO);

        builder.connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return builder;
    }
}
