package com.practice.myhome.fakeInstar.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.myhome.fakeInstar.util.Util;

public class BindingAdapters {

    @BindingAdapter({"imgUrl"})
    public static void imgUrl(ImageView view, String imgUrl){
        Util.loadImageOnByFinalUrl(imgUrl, view);
    }

    @BindingAdapter({"imgUrl", "borderRadius"})
    public static void imgUrl(ImageView view, String imgUrl, int borderRadius){
        Util.loadImageOnByFinalUrl(imgUrl, view, borderRadius);
    }

    @BindingAdapter({"adapter"})
    public static void adapter(RecyclerView view, RecyclerView.Adapter adapter){
        view.setAdapter(adapter);
    }

}
