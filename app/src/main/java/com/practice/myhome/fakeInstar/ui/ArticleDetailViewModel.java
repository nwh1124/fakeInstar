package com.practice.myhome.fakeInstar.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.myhome.fakeInstar.dto.Article;
import com.practice.myhome.fakeInstar.service.ArticleService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ArticleDetailViewModel extends ViewModel {

    private int id;
    private ArticleService articleService;
    public MutableLiveData<Article> lvArticle = new MutableLiveData<>();

    public void setId(int id){
        this.id = id;

        articleService.usr_article_detail(id, rb -> {
            lvArticle.setValue(rb.body.article);
        });
    }

    @Inject
    public ArticleDetailViewModel(ArticleService articleService){
        this.articleService = articleService;
    }
}
