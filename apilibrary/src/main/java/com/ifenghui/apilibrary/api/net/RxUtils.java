package com.ifenghui.apilibrary.api.net;

import android.widget.Toast;

import com.ifenghui.apilibrary.api.dto.RespModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/7.
 */
public class RxUtils {
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 线程调度器
     */
    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    //处理错误的变换
    public static  <T>  ObservableTransformer <T, T> exceptionTransformer() {

        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
                return (Observable<T>) observable.map(new HandleFuc()).onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            ResponseThrowable exception = ExceptionHandler.handleException(t);
            if(exception.code ==  ExceptionHandler.SYSTEM_ERROR.TIMEOUT_ERROR ){
//                Toast.makeText(RetrofitManager.mContext,"网络不给力哦！",Toast.LENGTH_SHORT).show();
            }
            return Observable.error(exception);
        }
    }

    private static class HandleFuc implements Function<Object,Object> {

        @Override
        public Object apply(Object o) throws Exception {
            if(o instanceof RespModel){
                RespModel respDTO = (RespModel) o;
                if(respDTO.getStatus().getCode() != ExceptionHandler.APP_ERROR.SUCC){
//                    Toast.makeText(RetrofitManager.mContext,respDTO.error,Toast.LENGTH_SHORT).show();
                }
            }
            return o;
        }
    }
}