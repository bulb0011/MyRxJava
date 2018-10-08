package www.android.com.myrxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by sunjays on 2018/9/17.
 */

public class TextRxJava {

    private static String TAG=TextRxJava.class.getCanonicalName();

    public void oneRx(){

        // 1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // create() 是 RxJava 最基本的创造事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        //<--方式1：采用Observer 接口 -->
        // 1. 创建观察者 （Observer ）对象
        Observer<Integer> observer = new Observer<Integer>() {
        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "开始采用subscribe连接");
                }

                // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onNext(Integer value) {
                    Log.d(TAG, "对Next事件作出响应" + value);
                }

                // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "对Error事件作出响应");
                }

                // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onComplete() {
                    Log.d(TAG, "对Complete事件作出响应");
                }
        };

        observable.subscribe(observer);

    }

    /**
     *链式调用
     */
    public void chainedRx(){



        // RxJava的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为

            Disposable mDisposable;//Disposable.dispose() 切断观察者 与 被观察者 之间的连接
            @Override
            public void onSubscribe(Disposable d) {

                mDisposable=d;

                Log.d(TAG, "开始采用subscribe连接");
            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {

                if (value==0){
                    mDisposable.dispose();
                }

                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        });
    }

}
