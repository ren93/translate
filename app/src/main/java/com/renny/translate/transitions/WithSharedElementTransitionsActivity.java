package com.renny.translate.transitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;

import com.renny.translate.R;
import com.renny.translate.listener.SimpleTransitionListener;

public class WithSharedElementTransitionsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Transitions--","BBBBActivity---onCreate");
        Slide slideEnter=new Slide();
        slideEnter.setDuration(1500);
        slideEnter.setSlideEdge(Gravity.RIGHT);
        slideEnter.addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                super.onTransitionStart(transition);
                Log.d("Transitions--","EnterTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                Log.d("Transitions--","EnterTransitionEnd");
            }
        });
        getWindow().setEnterTransition(slideEnter);
        Slide slide=new Slide();
        slide.setDuration(1500);
        slide.setSlideEdge(Gravity.RIGHT);

        slide.addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                super.onTransitionStart(transition);
                Log.d("Transitions--","ReturnTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                Log.d("Transitions--","ReturnTransitionEnd");
            }
        });

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(5000).addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementEnterTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementEnterTransitionEnd");

            }
        });
        ChangeBounds changeBounds2 = new ChangeBounds();
        changeBounds2.setDuration(5000).addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementReturnTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("TransitionsEle--", "SharedElementReturnTransitionEnd");

            }
        });
        getWindow().setReturnTransition(slide);
       getWindow().setSharedElementEnterTransition(changeBounds);
       getWindow().setSharedElementReturnTransition( changeBounds2);
        setContentView(R.layout.activity_with_shared_element_transitions);
        initToolbar();
    }

    @Override
    protected void onStart() {
        Log.d("Transitions--","BBBBActivity---onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("Transitions--","BBBBActivity---onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("Transitions--","BBBBActivity---onStop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("Transitions--","BBBBActivity---onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Transitions--","BBBBActivity---onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("Transitions--","BBBBActivity---onDestroy");
        super.onDestroy();
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
