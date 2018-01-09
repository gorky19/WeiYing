package http;

import java.util.Map;

import Bean.FuliBean;
import Bean.RootBean;
import Bean.RootDataBean;
import Bean.SelectBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public interface ApiService {
    @GET("homePageApi/homePage.do")
    Flowable<RootBean> getService(@QueryMap Map<String,String> map);
    @GET("columns/getVideoList.do")
    Flowable<RootDataBean> getZhuan(@QueryMap Map<String,String> map);
    @GET("searchKeyWordApi/getVideoListByKeyWord.do")
    Flowable<SelectBean> getSelect(@QueryMap Map<String,String> map);
    @GET("http://www.gank.io/api/data/福利/10/{pages}")
    Flowable<FuliBean> getFuli(
            @Path("pages") int pages);
}
