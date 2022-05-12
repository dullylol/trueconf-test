package com.degalex.trueconftest.ui.base;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BaseFragment extends Fragment {

    protected <VM extends BaseViewModel> VM getViewModel(Class<VM> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }
}