package http;

import java.util.Map;

import Bean.CommentaryBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public interface ApiService3 {
    @GET("Commentary/getCommentList.do")
    Flowable<CommentaryBean> getCommentary(@QueryMap Map<String, String> map);
}
