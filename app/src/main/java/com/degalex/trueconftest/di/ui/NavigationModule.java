package com.degalex.trueconftest.di.ui;

import com.degalex.trueconftest.ui.navigation.ActivityHolder;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class NavigationModule {

    @Provides
    @ActivityRetainedScoped
    ActivityHolder provideActivityHolder() {
        return new ActivityHolder();
    }
}
