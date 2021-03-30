package com.practice.myhome.fakeInstar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.practice.myhome.fakeInstar.R;
import com.practice.myhome.fakeInstar.databinding.FragmentHomeMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeMainFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState){
        HomeMainViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeMainViewModel.class);

        FragmentHomeMainBinding binding = FragmentHomeMainBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setVm(homeViewModel);

        return binding.getRoot();
    }

}
