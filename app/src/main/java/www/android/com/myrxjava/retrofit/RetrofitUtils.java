package www.android.com.myrxjava.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sunjays on 2018/10/22.
 */

public class RetrofitUtils {

    private  OkHttpClient.Builder builder ;
            Retrofit retrofit;
    private static ApiService apiservice;

    public RetrofitUtils() {
        RetrofitConfiguration retrofitConfiguration=new RetrofitConfiguration();
        builder = retrofitConfiguration.getBuilder();
        init();
    }

    private void init(){

         retrofit = new Retrofit.Builder()
                .baseUrl("http://61.185.99.167:9982")
                .addConverterFactory(GsonConverterFactory.create())
//                 .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        apiservice=retrofit.create(ApiService.class);

    }

    public static ApiService getApiservice() {
        if (apiservice!=null){
            return apiservice;
        }
        return null;
    }
}
