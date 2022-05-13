package com.degalex.trueconftest.ui.screens;

import com.degalex.trueconftest.R;
import com.degalex.trueconftest.ui.base.BaseRouter;
import com.degalex.trueconftest.ui.navigation.ActivityHolder;
import com.degalex.trueconftest.ui.screens.hello.HelloFragment;

import javax.inject.Inject;

class MainRouter extends BaseRouter {

    @Inject
    public MainRouter(ActivityHolder activityHolder) {
        super(activityHolder);
    }

    void openHelloScreen() {
        replaceFragment(R.id.activityFragmentContainer, HelloFragment.newInstance());
    }
}
