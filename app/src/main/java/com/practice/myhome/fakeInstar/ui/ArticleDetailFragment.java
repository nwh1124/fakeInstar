package com.practice.myhome.fakeInstar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.practice.myhome.fakeInstar.databinding.FragmentArticleDetailBinding;
import com.practice.myhome.fakeInstar.util.Util;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ArticleDetailFragment extends Fragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState){

        int id = getArguments().getInt("id", 0);
        Util.toast(id + "번");

        ArticleDetailViewModel vm =
                new ViewModelProvider(this).get(ArticleDetailViewModel.class);

        vm.setId(id);

        FragmentArticleDetailBinding binding = FragmentArticleDetailBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setVm(vm);

        return binding.getRoot();
    }
}
