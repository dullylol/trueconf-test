package com.degalex.trueconftest.ui.screens;

import com.degalex.trueconftest.ui.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
class MainViewModel extends BaseViewModel {

    private MainRouter router;

    @Inject
    public MainViewModel(MainRouter router) {
        this.router = router;
    }
}
