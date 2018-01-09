package Model;

import Bean.FuliBean;
import Presenter.Data_Pre2;
import http.ApiService;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2018/1/3.
 */

public class Model_Fuli implements IVModel2{
    private Data_Pre2 mpre2;
    public Model_Fuli(Data_Pre2 data_pre2) {
        this.mpre2 = data_pre2;
    }
    @Override
    public void getDatas(String baseurl,int pages) {
        Flowable<FuliBean> fuli = RetrofitUitls.getInstance(baseurl).getretrofit()
                .create(ApiService.class).getFuli(pages);
        mpre2.getNewsFuli(fuli);
    }
}
