package cn.caoleix.all.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RequestManager {

    private RequestManager() { }

    public static RequestManager instance() {
        return Holder.instance;
    }

    private static class Holder {
        private final static RequestManager instance = new RequestManager();
    }

    /**
     * 开始异步请求
     */
    public <T> Disposable async(Observable observable, final Observer<T> observer) {
        Consumer<T> onNext = new Consumer<T>() {
            @Override
            public void accept(T result) throws Exception {
                observer.onNext(result);
            }
        };
        Consumer<Throwable> onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {
                observer.onError(e);
            }
        };
        Action onComplete = new Action() {
            @Override
            public void run() throws Exception {
                observer.onComplete();
            }
        };
        Consumer<Disposable> onSubscribe = new Consumer<Disposable>() {
            @Override
            public void accept(Disposable d) throws Exception {
                observer.onSubscribe(d);
            }
        };
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete, onSubscribe);
    }


}
