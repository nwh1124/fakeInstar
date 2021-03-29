package com.practice.myhome.fakeInstar.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.practice.myhome.fakeInstar.util.Util;

public class BindingAdapters {

    @BindingAdapter({"imgUrl"})
    public static void imgUrl(ImageView view, String imgUrl){
        Util.loadImageOn(imgUrl, view);
    }

    @BindingAdapter({"imgUrl", "borderRadius"})
    public static void imgUrl(ImageView view, String imgUrl, int borderRadius){
        Util.loadImageOn(imgUrl, view, borderRadius);
    }

}
