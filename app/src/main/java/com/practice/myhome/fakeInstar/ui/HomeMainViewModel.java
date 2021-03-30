package com.practice.myhome.fakeInstar.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeMainViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeMainViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("날이 참 좋네요.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}