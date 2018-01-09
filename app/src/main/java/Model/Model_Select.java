package Model;

import java.util.Map;

import Bean.SelectBean;
import Presenter.Data_Pre3;
import http.ApiService;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Model_Select implements IVModel3{
    private Data_Pre3 mpre3;
    public Model_Select(Data_Pre3 data_pre3) {
        this.mpre3 = data_pre3;
    }
    @Override
    public void getDatas(String baseurl, Map<String, String> map) {
        Flowable<SelectBean> select = RetrofitUitls.getInstance(baseurl).getretrofit()
                .create(ApiService.class).getSelect(map);
        mpre3.getNewData_Select(select);
    }
}
