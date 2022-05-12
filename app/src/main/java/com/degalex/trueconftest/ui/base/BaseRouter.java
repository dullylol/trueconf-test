package com.degalex.trueconftest.ui.base;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.degalex.trueconftest.ui.navigation.ActivityHolder;

public abstract class BaseRouter {

    private ActivityHolder activityHolder;

    public BaseRouter(ActivityHolder activityHolder) {
        this.activityHolder = activityHolder;
    }

    protected void startActivity(Intent intent) {
        activityHolder.getActivity().startActivity(intent);
    }

    protected void openFragment(
            @IdRes int containerViewId,
            Fragment fragment
    ) {
        activityHolder
                .getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(null);
    }

    protected void replaceFragment(
            @IdRes int containerViewId,
            Fragment fragment
    ) {
        activityHolder
                .getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    protected void back() {
        activityHolder.getActivity().onBackPressed();
    }
}
