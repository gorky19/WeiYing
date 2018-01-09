package Presenter;

import android.util.Log;

import Bean.FuliBean;
import Model.Model_Fuli;
import Views.IView2;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Data_Pre2 implements IVPre2{
    private IView2 miv2;
    private DisposableSubscriber subscriber;
    public void attach(IView2 iv2) {
        this.miv2 = iv2;
    }
    public void detach() {
        if(subscriber != null) {
            if(subscriber.isDisposed()) {
                subscriber.dispose();
            }
        }
    }
    @Override
    public void getData(String baseurl,int pages) {
        Model_Fuli model_fuli = new Model_Fuli(this);
        model_fuli.getDatas(baseurl,pages);
    }

    public void getNewsFuli(final Flowable<FuliBean> fuli) {
        subscriber = fuli.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<FuliBean>() {
                    @Override
                    public void onNext(FuliBean fuliBean) {
                        if(fuliBean != null) {
                            miv2.onsuccess2(fuliBean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                         miv2.onfailed2((Exception) t);
                        Log.d("zzz",t.getMessage().toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
