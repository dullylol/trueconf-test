package com.degalex.trueconftest.ui.screens;

import androidx.appcompat.app.AppCompatActivity;

import com.degalex.trueconftest.ui.base.BaseViewModel;
import com.degalex.trueconftest.ui.navigation.ActivityHolder;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
class MainViewModel extends BaseViewModel {

    private MainRouter router;
    private ActivityHolder activityHolder;

    @Inject
    public MainViewModel(
            MainRouter router,
            ActivityHolder activityHolder
    ) {
        this.router = router;
        this.activityHolder = activityHolder;
    }

    public void onAttachActivity(AppCompatActivity activity) {
        activityHolder.attachActivity(activity);
    }

    public void onDetachActivity() {
        activityHolder.detachActivity();
    }

    public void onFirstLaunch() {
        router.openHelloScreen();
    }
}
