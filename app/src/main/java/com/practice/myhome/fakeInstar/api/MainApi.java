package com.practice.myhome.fakeInstar.api;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    String baseUrl = "http://10.0.2.2:8021/";

    @GET("usr/article/list")
    Observable<MainApi__RB<MainApi__usr_article_list_RBB>> usr_article_list(@Query("boardId") int boardId, @Query("page") int page);

    @GET("usr/article/detail")
    Observable<MainApi__RB<MainApi__usr_article_detail_RBB>> usr_article_detail(@Query("id") int id);

}
