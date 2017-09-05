package com.renny.translate.transitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.renny.translate.R;
import com.renny.translate.helper.CircularRevealAnimator;
import com.renny.translate.listener.SimpleTransitionListener;

public class TransitionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("xxxx", "onCreate");
        initToolbar();
        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final float scale = getResources().getDisplayMetrics().density;

        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setSlideEdge(Gravity.LEFT);
        slide.addListener(new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("xxxx", "onTransitionStart");
                CircularRevealAnimator.builder(findViewById(R.id.image_bgg))
                        .setDuration(1500)
                        .setCenter(0, 0)
                        .setStartRadius(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setEndRadius((float) Math.hypot(dm.widthPixels, (int) (260 * scale + 0.5f)))
                        .start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("xxxx", "onTransitionEnd");

            }

        });

        getWindow().setEnterTransition(slide);

        Explode explode2 = new Explode();
        explode2.setDuration(700);
        explode2.setMode(Visibility.MODE_OUT);
        explode2.setInterpolator(new BounceInterpolator());
        getWindow().setReturnTransition(explode2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("xxxx", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("xxxx", "onResume");
    }

    public void fiis(View view) {
        finish();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
