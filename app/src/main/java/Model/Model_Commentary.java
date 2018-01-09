package Model;

import java.util.Map;

import Bean.CommentaryBean;
import Presenter.Data_Presenter;
import http.ApiService3;
import http.RetrofitUitls;
import io.reactivex.Flowable;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public class Model_Commentary implements IVModel{
    private Data_Presenter mdata_presenter;
    public Model_Commentary(Data_Presenter data_presenter) {
        this.mdata_presenter = data_presenter;
    }

    @Override
    public void getDatas(String baseurl, Map<String, String> map) {
        Flowable<CommentaryBean> flowable_commentary = RetrofitUitls.getInstance(baseurl).getretrofit().create(ApiService3.class).getCommentary(map);
        mdata_presenter.getNewDatas3(flowable_commentary);
    }
}
