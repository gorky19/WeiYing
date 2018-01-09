package Model;

import java.util.Map;

import Bean.VideoBean;
import Presenter.Data_Presenter;
import http.ApiService2;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public class Model_Video implements IVModel{
    private Data_Presenter mdata_presenter;
    public Model_Video(Data_Presenter data_presenter) {
        this.mdata_presenter = data_presenter;
    }

    @Override
    public void getDatas(String baseurl, Map<String, String> map) {
        Flowable<VideoBean> flowable_video = RetrofitUitls.getInstance(baseurl).getretrofit().create(ApiService2.class).getVideo(map);
        mdata_presenter.getNewDatas2(flowable_video);
    }
}
