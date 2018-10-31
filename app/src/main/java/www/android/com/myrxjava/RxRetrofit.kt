package www.android.com.myrxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.android.com.myrxjava.bean.Rben
import www.android.com.myrxjava.retrofit.RetrofitUtils
import www.android.com.myrxjava.retrofit.RetryWithDelay
import java.util.concurrent.TimeUnit

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
//        http()

        /**
         *
         * 观察者模式请求网络
         *
        @POST("/nyxt/ImplementAnalysis/selectCabin")
        Observable<List<Rben>> getData();
         */
//        ObserverHttp()

        /**
         * 无限次请求网络
         * @POST("/nyxt/ImplementAnalysis/selectCabin")
        Observable<List<Rben>> getGc();
         */
//        repetitionHttp()


        /**
         * 间隔联网
         * @POST("/nyxt/ImplementAnalysis/selectCabin")
            Observable<List<Rben>> getData();
         */
//        intervalHttp()
    }

    private fun intervalHttp() {
        RetrofitUtils.getApiservice()
                .data
                //总共重试3次，重试间隔3000毫秒
                .retryWhen(object : RetryWithDelay(3, 3000) {})
                .subscribeOn(Schedulers.io())// 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())// 切换回到主线程 处理请求结果
                .subscribe(object : Observer<List<Rben>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Rben>) {

                        Log.i("onResponse", "" + t.get(0).dyName)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun http() {
        RetrofitUtils.getApiservice()
                .getPt()
                .enqueue(object : Callback<List<Rben>> {
                    override fun onFailure(call: Call<List<Rben>>?, t: Throwable?) {
                        Log.i("onFailure", "onFailure")

                    }

                    override fun onResponse(call: Call<List<Rben>>?, response: Response<List<Rben>>?) {
                        Log.i("onResponse", "" + response?.body()?.get(0)?.dyName)
                    }
                })
    }

    private fun ObserverHttp() {
        RetrofitUtils.getApiservice()
                .gc
                .subscribeOn(Schedulers.io())// 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())// 切换回到主线程 处理请求结果
                .subscribe(object : Observer<List<Rben>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Rben>) {
                        Log.i("onNext", "onNext" + t.get(0).dyName)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("onError", "onError" + e.message)
                    }
                })
    }

    private fun repetitionHttp() {
        Observable.interval(1, 2, TimeUnit.SECONDS)
                // 参数说明：
                // 参数1 = 第1次延迟时间；
                // 参数2 = 间隔时间数字；
                // 参数3 = 时间单位；
                // 该例子发送的事件特点：延迟1s后发送事件，每隔2秒产生1个数字（从0开始递增1，无限个）
                .doOnNext(object : Consumer<Long> {

                    override fun accept(t: Long?) {
                        Log.i("dengpao", "第几次" + t)

                        RetrofitUtils.getApiservice()
                                .gc
                                .subscribeOn(Schedulers.io())// 切换到IO线程进行网络请求
                                .observeOn(AndroidSchedulers.mainThread())// 切换回到主线程 处理请求结果
                                .subscribe(object : Observer<List<Rben>> {
                                    override fun onComplete() {
                                    }

                                    override fun onSubscribe(d: Disposable) {
                                    }

                                    override fun onNext(t: List<Rben>) {
                                        Log.i("onNext", "onNext" + t.get(0).dyName)
                                    }

                                    override fun onError(e: Throwable) {
                                        Log.i("onError", "onError" + e.message)
                                    }
                                })
                    }

                }).subscribe(object : Observer<Long> {


                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Long) {
                        Log.i("dengpao", "Long" + Long.toString())
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

}