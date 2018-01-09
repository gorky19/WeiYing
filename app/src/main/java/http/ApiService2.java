package http;

import java.util.Map;

import Bean.VideoBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public interface ApiService2 {
    @GET("videoDetailApi/videoDetail.do")
    Flowable<VideoBean> getVideo(@QueryMap Map<String,String> map);
}
