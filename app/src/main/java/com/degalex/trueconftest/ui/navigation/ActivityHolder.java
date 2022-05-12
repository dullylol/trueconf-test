package com.degalex.trueconftest.ui.navigation;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHolder {

    private AppCompatActivity activity = null;

    public AppCompatActivity getActivity() {
        return activity;
    }

    void attachActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    void detachActivity() {
        activity = null;
    }
}
