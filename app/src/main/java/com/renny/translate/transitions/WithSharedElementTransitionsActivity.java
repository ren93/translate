package com.renny.translate.transitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.renny.translate.R;
import com.renny.translate.helper.CircularRevealAnimator;

public class WithSharedElementTransitionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout liney_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_shared_element_transitions);
        initView();
        initToolbar();
        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(3000));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float scale = getResources().getDisplayMetrics().density;
        CircularRevealAnimator.builder(findViewById(R.id.relay_flag))
                .setAfterVisibility(true)
                .setDuration(1500)
                .setCenter(0, 0)
                .setStartRadius(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setEndRadius((float) Math.hypot(dm.widthPixels, (int) (260 * scale + 0.5f)))
                .start();
    }


    private void initView() {
        liney_bottom = (LinearLayout) findViewById(R.id.liney_bottom);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


}
