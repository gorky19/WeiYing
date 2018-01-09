package Presenter;

import java.util.Map;

import Bean.SelectBean;
import Model.Model_Select;
import Views.IView3;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Data_Pre3 implements IVPre{
    private IView3 miv3;
    private DisposableSubscriber subscriber;
    public void attach(IView3 iv3) {
        this.miv3 = iv3;
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
        Model_Select model_select = new Model_Select(this);
        model_select.getDatas(baseurl,map);
    }

    public void getNewData_Select(Flowable<SelectBean> tuijian) {
        subscriber = tuijian.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SelectBean>() {
                    @Override
                    public void onNext(SelectBean bean) {
                        if(bean != null) {
                            miv3.onsuccess3(bean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv3.onfailed3((Exception) t);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
