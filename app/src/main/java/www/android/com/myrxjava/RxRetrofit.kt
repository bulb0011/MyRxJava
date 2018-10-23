package www.android.com.myrxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import www.android.com.myrxjava.bean.Rben
import www.android.com.myrxjava.retrofit.RetrofitUtils

/**
 * Created by sunjays on 2018/10/22.
 */
open class RxRetrofit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        /**
         * 普通的
         *
         @POST("/nyxt/ImplementAnalysis/selectCabin")
         Call<List<Rben>> getData();
         */
//        RetrofitUtils.getApiservice()
//                .data
//                .enqueue(object : Callback<List<Rben>> {
//                    override fun onFailure(call: Call<List<Rben>>?, t: Throwable?) {
//                        Log.i("onFailure","onFailure")
//
//                    }
//
//                    override fun onResponse(call: Call<List<Rben>>?, response: Response<List<Rben>>?) {
//                        Log.i("onResponse",""+response?.body()?.get(0)?.dyName)
//                    }
//                })

        RetrofitUtils.getApiservice()
                .data.subscribeOn(Schedulers.io())// 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Rben>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Rben>) {
                        Log.i("onNext","onNext"+t.get(0).dyName)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("onError","onError"+e.message)
                    }
                })


    }

}