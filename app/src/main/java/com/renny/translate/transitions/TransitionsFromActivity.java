package com.renny.translate.transitions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.renny.translate.R;
import com.renny.translate.listener.SimpleTransitionListener;

public class TransitionsFromActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView shared_image;
    private TextView shared_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Transitions--", "AAAAActivity---onCreate");
        getWindow().setAllowEnterTransitionOverlap(false);
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.BOTTOM);
        slide.setDuration(3000);
        slide.addListener(new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                super.onTransitionStart(transition);
                Log.d("Transitions--", "ExitTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                Log.d("Transitions--", "ExitTransitionEnd");
            }
        });
        getWindow().setExitTransition(slide);

        Explode explode = new Explode();
        explode.setDuration(3000);
        explode.setMode(Visibility.MODE_IN);
        explode.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                Log.d("Transitions--", "ReenterTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {

                Log.d("Transitions--", "ReenterTransitionEnd");
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setReenterTransition(explode);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(5000).addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementExitTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementExitTransitionEnd");

            }
        });
        getWindow().setSharedElementExitTransition(changeBounds);
        ChangeBounds changeBounds2 = new ChangeBounds();
        changeBounds2.setDuration(5000).addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementReenterTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementReenterTransitionEnd");

            }
        });
        getWindow().setSharedElementsUseOverlay(false);
        getWindow().setSharedElementReenterTransition(changeBounds2);
        setContentView(R.layout.element_transitions_from);
        initToolbar();
        shared_image = (ImageView) findViewById(R.id.shared_image);
        shared_text = (TextView) findViewById(R.id.shared_text);
    }


    public void withSharedFrom(View view) {
        Intent intent = new Intent(this, WithSharedElementTransitionsActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                , new Pair<View, String>(shared_image, "shared_image_")
                , new Pair<View, String>(shared_text, "shared_text_"));
        startActivity(intent, activityOptionsCompat.toBundle());
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

    @Override
    protected void onStart() {
        Log.d("Transitions--", "AAAAActivity---onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("Transitions--", "AAAAActivity---onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("Transitions--", "AAAAActivity---onStop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("Transitions--", "AAAAActivity---onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Transitions--", "AAAAActivity---onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("Transitions--", "AAAAActivity---onDestroy");
        super.onDestroy();
    }

}
