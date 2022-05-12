package com.degalex.trueconftest.ui.screens;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.degalex.trueconftest.databinding.ActivityMainBinding;
import com.degalex.trueconftest.ui.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = getViewModel(MainViewModel.class);
    }
}
