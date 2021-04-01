package com.practice.myhome.fakeInstar.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.myhome.fakeInstar.dto.Article;
import com.practice.myhome.fakeInstar.service.ArticleService;
import com.practice.myhome.fakeInstar.util.Util;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeMainViewModel extends ViewModel {

    public RecyclerViewAdapterArticle recyclerViewAdapterArticle;
    public MutableLiveData<Article> lvFirstArticle = new MutableLiveData<>();

    @Inject
    public HomeMainViewModel(ArticleService articleService) {
        recyclerViewAdapterArticle = new RecyclerViewAdapterArticle();

        recyclerViewAdapterArticle.setOnClickItem((v) -> {
            int articleIndex = (int)v.getTag();
            Article article = recyclerViewAdapterArticle.getArticle(articleIndex);

            Util.toast(article.id + "번 게시물");
        });

        articleService.usr_article_list(1, 1, rb -> {
            recyclerViewAdapterArticle.addArticles(rb.body.articles);
        });
    }
}