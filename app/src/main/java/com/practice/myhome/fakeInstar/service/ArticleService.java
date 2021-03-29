package com.practice.myhome.fakeInstar.service;

import androidx.annotation.NonNull;

import com.practice.myhome.fakeInstar.api.MainApi;
import com.practice.myhome.fakeInstar.api.MainApi__RB;
import com.practice.myhome.fakeInstar.api.MainApi__usr_article_list_RBB;
import com.practice.myhome.fakeInstar.util.Util;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticleService {
    private MainApi mainApi;

    @Inject
    public ArticleService(MainApi mainApi){
        this.mainApi = mainApi;
    }

    public void usr_article_list(int boardId, int page, @NonNull Consumer<? super MainApi__RB<MainApi__usr_article_list_RBB>> onNext){
        mainApi.usr_article_list(boardId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> {
                    Util.log("throwable : " + throwable.getMessage());
                });
    }

}
