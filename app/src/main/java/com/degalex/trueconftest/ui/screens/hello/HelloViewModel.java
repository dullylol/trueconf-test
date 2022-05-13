package com.degalex.trueconftest.ui.screens.hello;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.degalex.trueconftest.ui.base.BaseViewModel;
import com.degalex.trueconftest.ui.utils.enums.ViewBehaviour;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class HelloViewModel extends BaseViewModel {

    private static final int startAnimationDelay = 5;

    private final MutableLiveData<ViewBehaviour> viewBehaviourMutableLiveData = new MutableLiveData<ViewBehaviour>();
    final LiveData<ViewBehaviour> viewBehaviourLiveData = viewBehaviourMutableLiveData;

    private Disposable startAnimationDisposable;

    void onFieldClicked() {
        disposeAnimation();

        startAnimationDisposable = Observable
                .fromAction(() -> viewBehaviourMutableLiveData.postValue(ViewBehaviour.MoveDown))
                .delaySubscription(startAnimationDelay, TimeUnit.SECONDS)
                .subscribe();

        compositeDisposable.add(startAnimationDisposable);
    }

    void onTextClicked() {
        viewBehaviourMutableLiveData.postValue(ViewBehaviour.Stay);
    }

    void onAnimationEnded() {
        if (viewBehaviourLiveData.getValue() == ViewBehaviour.MoveDown) {
            viewBehaviourMutableLiveData.postValue(ViewBehaviour.MoveUp);
        } else if (viewBehaviourLiveData.getValue() == ViewBehaviour.MoveUp) {
            viewBehaviourMutableLiveData.postValue(ViewBehaviour.MoveDown);
        }
    }

    public void onAnimationCanceled() {
        disposeAnimation();
        viewBehaviourMutableLiveData.setValue(null);
    }

    private void disposeAnimation() {
        if (startAnimationDisposable != null && !startAnimationDisposable.isDisposed()) {
            startAnimationDisposable.dispose();
        }
    }
}
