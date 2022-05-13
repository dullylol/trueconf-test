package com.degalex.trueconftest.ui.navigation;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHolder {

    private AppCompatActivity activity = null;

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void attachActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void detachActivity() {
        activity = null;
    }
}
