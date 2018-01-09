package Presenter;

import java.util.Map;

import Bean.CommentaryBean;
import Bean.RootBean;
import Bean.RootDataBean;
import Bean.SelectBean;
import Bean.VideoBean;
import Model.Model_Commentary;
import Model.Model_Data;
import Model.Model_Video;
import Views.IView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Data_Presenter implements IVPre{
    private IView miv;
    private DisposableSubscriber subscriber;
    private DisposableSubscriber subscriber2;
    private DisposableSubscriber subscriber3;
    private DisposableSubscriber sub_zhuan;
    private DisposableSubscriber sub_select;
    public void attach(IView iv) {
        this.miv = iv;
    }
    public void detach() {
        if(miv != null) {
            miv = null;
        }
        if(subscriber != null) {
           if(!subscriber.isDisposed()) {
               subscriber.dispose();
           }
        }
        if(subscriber2 != null) {
            if(!subscriber2.isDisposed()) {
                subscriber2.dispose();
            }
        }
        if(subscriber3 != null) {
            if(!subscriber3.isDisposed()) {
                subscriber3.dispose();
            }
        }
        if(sub_zhuan != null) {
            if(!sub_zhuan.isDisposed()) {
                sub_zhuan.dispose();
            }
        }
        if(sub_select != null) {
            if(!sub_select.isDisposed()) {
                sub_select.dispose();
            }
        }
    }
    @Override
    public void getData(String baseurl, Map<String,String> map) {
        Model_Data model_data = new Model_Data(this);
        Model_Video model_video = new Model_Video(this);
        Model_Commentary model_commentary = new Model_Commentary(this);
        model_data.getDatas(baseurl,map);
        model_video.getDatas(baseurl,map);
        model_commentary.getDatas(baseurl,map);

    }
    public void getNewDatas(Flowable<RootBean> flowable) {
        subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RootBean>() {
                    @Override
                    public void onNext(RootBean rootBean) {
                        if(rootBean != null) {
                            miv.onsuccess(rootBean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv.onfailed((Exception) t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getNewDatas2(Flowable<VideoBean> flowable_video) {
        subscriber2 = flowable_video.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoBean>() {
                    @Override
                    public void onNext(VideoBean videoBean) {
                        if(videoBean != null) {
                            miv.onsuccess(videoBean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv.onfailed((Exception) t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getNewDatas3(Flowable<CommentaryBean> flowable_commentary) {
        subscriber3 = flowable_commentary.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<CommentaryBean>(){
                    @Override
                    public void onNext(CommentaryBean commentaryBean) {
                        if(commentaryBean != null) {
                            miv.onsuccess(commentaryBean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getNewZhuan(Flowable<RootDataBean> zhuan) {
        sub_zhuan = zhuan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RootDataBean>(){
                    @Override
                    public void onNext(RootDataBean bean) {
                        if(bean != null) {
                            miv.onsuccess(bean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv.onfailed((Exception) t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getNewSelect(Flowable<SelectBean> select) {
        sub_select = select.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SelectBean>(){
                    @Override
                    public void onNext(SelectBean bean) {
                        if(bean != null) {
                            miv.onsuccess(bean);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        miv.onfailed((Exception) t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
