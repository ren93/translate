package com.renny.translate;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getWindow().setEnterTransition(new Slide().setDuration(2000));
        getWindow().setExitTransition(new Slide().setDuration(2000));
    }

    public void circle(View view) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                view,
                0,
                0,
                0,
                (float) Math.hypot(view.getWidth(), view.getHeight()));
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
}
