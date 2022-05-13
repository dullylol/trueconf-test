package com.degalex.trueconftest.ui.screens.hello;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.degalex.trueconftest.databinding.FragmentHelloBinding;
import com.degalex.trueconftest.ui.base.BaseFragment;
import com.degalex.trueconftest.ui.utils.enums.ViewBehaviour;

import java.util.Locale;

public class HelloFragment extends BaseFragment {

    private final static int durationTime = 3000;
    private final static String translationYProperty = "translationY";

    private FragmentHelloBinding binding;
    private HelloViewModel helloViewModel;

    private ObjectAnimator objectAnimator;

    private final Animator.AnimatorListener objectAnimatorListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animator) {
            // None
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            helloViewModel.onAnimationEnded();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            helloViewModel.onAnimationCanceled();
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            // None
        }
    };

    private final View.OnTouchListener handleTouch = new View.OnTouchListener() {

        private boolean downTouched;
        private boolean moveTouched;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                downTouched = true;
            } else if (action == MotionEvent.ACTION_MOVE) {
                moveTouched = true;
            } else if (action == MotionEvent.ACTION_UP) {
                if (downTouched && !moveTouched) {
                    view.performClick();
                    handleFieldTouch(motionEvent.getX(), motionEvent.getY());
                }
                downTouched = false;
                moveTouched = false;
            }

            return true;
        }
    };

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentHelloBinding.inflate(inflater, container, false);
        helloViewModel = getViewModel(HelloViewModel.class);

        setupListeners();
        setupObservers();

        return binding.getRoot();
    }

    private void setupListeners() {
        binding.helloTextView.setOnClickListener(view -> helloViewModel.onTextClicked());

        binding.getRoot().setOnTouchListener(handleTouch);
    }

    private void setupObservers() {
        helloViewModel.viewBehaviourLiveData.observe(getViewLifecycleOwner(), (viewBehaviour -> {
            if (viewBehaviour == ViewBehaviour.Stay) {
                cancelAnimation();
            } else if (viewBehaviour == ViewBehaviour.MoveUp) {
                float moveValue = -getScreenHeight() / 2 + binding.helloTextView.getHeight();
                moveViewVerticallyWithAnimation(moveValue);
            } else if (viewBehaviour == ViewBehaviour.MoveDown) {
                float moveValue = getScreenHeight() / 2 - binding.helloTextView.getHeight();
                moveViewVerticallyWithAnimation(moveValue);
            }
        }));
    }

    private void handleFieldTouch(float x, float y) {
        changeViewColorByLocale();
        cancelAnimation();
        clearViewTranslationY();
        moveViewToLocation(x, y);
        helloViewModel.onFieldClicked();
    }

    private void changeViewColorByLocale() {
        if (getCurrentLocale().getLanguage().equals("ru")) {
            binding.helloTextView.setTextColor(Color.BLUE);
        } else {
            binding.helloTextView.setTextColor(Color.RED);
        }
    }

    private void moveViewVerticallyWithAnimation(Float value) {
        objectAnimator = ObjectAnimator.ofFloat(binding.helloTextView, translationYProperty, value);

        objectAnimator.setDuration(durationTime);
        objectAnimator.addListener(objectAnimatorListener);

        objectAnimator.start();
    }

    private void moveViewToLocation(float x, float y) {
        binding.helloTextView.setX(getCorrectedX(x - binding.helloTextView.getWidth() / 2f));
        binding.helloTextView.setY(getCorrectedY(y - binding.helloTextView.getHeight() / 2f));
    }

    private float getCorrectedX(float x) {
        float minX = 0;
        float maxX = getScreenWidth() - binding.helloTextView.getWidth();

        return Math.min(Math.max(x, minX), maxX);
    }

    private float getCorrectedY(float y) {
        float minY = 0;
        float maxY = getScreenHeight() - binding.helloTextView.getHeight() * 2;

        return Math.min(Math.max(y, minY), maxY);
    }

    private void clearViewTranslationY() {
        binding.helloTextView.setTranslationY(0);
    }

    private void cancelAnimation() {
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        binding.helloTextView.clearAnimation();
    }

    private float getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.heightPixels;
    }

    private float getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.widthPixels;
    }

    private Locale getCurrentLocale() {
        return getResources().getConfiguration().getLocales().get(0);
    }

    public static HelloFragment newInstance() {
        return new HelloFragment();
    }
}
