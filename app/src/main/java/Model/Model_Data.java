package Model;

import java.util.Map;

import Bean.RootBean;
import Bean.RootDataBean;
import Presenter.Data_Presenter;
import http.ApiService;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Model_Data implements IVModel{
    private Data_Presenter mdata_presenter;
    public Model_Data(Data_Presenter data_presenter) {
        this.mdata_presenter = data_presenter;
    }
    @Override
    public void getDatas(String baseurl, Map<String,String> map) {
        Flowable<RootBean> flowable = RetrofitUitls.getInstance(baseurl).getretrofit().create(ApiService.class).getService(map);
        mdata_presenter.getNewDatas(flowable);
        Flowable<RootDataBean> zhuan = RetrofitUitls.getInstance(baseurl).getretrofit().create(ApiService.class).getZhuan(map);
        mdata_presenter.getNewZhuan(zhuan);
    }

}
