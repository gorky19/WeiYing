package Presenter;

import java.util.Map;

import Bean.RootBean;
import Model.Model_Tui;
import Views.IView2;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Data_Pre implements IVPre{
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
    public void getData(String baseurl, Map<String, String> map) {
        Model_Tui model_tui = new Model_Tui(this);
        model_tui.getDatas(baseurl,map);
    }

    public void getNewData_Tui(Flowable<RootBean> tuijian) {
        subscriber = tuijian.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RootBean>() {
                    @Override
                    public void onNext(RootBean rootBean) {
                        if(rootBean != null) {
                            miv2.onsuccess2(rootBean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv2.onfailed2((Exception) t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
