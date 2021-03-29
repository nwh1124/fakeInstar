package com.practice.myhome.fakeInstar.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeMainViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeMainViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue(("홈 프래그먼트"));
    }

    public LiveData<String> getText() {
        return mText;
    }
}
