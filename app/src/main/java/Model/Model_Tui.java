package Model;

import java.util.Map;

import Bean.RootBean;
import Presenter.Data_Pre;
import Presenter.Data_Presenter;
import Presenter.IVPre;
import http.ApiService;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Model_Tui implements IVModel{
    private Data_Pre mpre;
    public Model_Tui(Data_Pre data_pre) {
        this.mpre = data_pre;
    }
    @Override
    public void getDatas(String baseurl, Map<String, String> map) {
        Flowable<RootBean> tuijian = RetrofitUitls.getInstance(baseurl).getretrofit().create(ApiService.class).getService(map);
        mpre.getNewData_Tui(tuijian);
    }
}
