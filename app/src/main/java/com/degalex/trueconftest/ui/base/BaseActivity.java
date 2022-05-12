package com.degalex.trueconftest.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity extends AppCompatActivity {

    protected <VM extends BaseViewModel> VM getViewModel(Class<VM> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }
}
