package com.renny.translate.shape;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;

import com.renny.translate.R;
import com.renny.translate.listener.SimpleTransitionListener;
import com.renny.translate.widget.TransPathView;


public class CustomSVGTransitionToActivity extends AppCompatActivity
        implements View.OnClickListener {

    TransPathView transView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_svg_tra);
        final ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(2000);
        changeBounds.addListener(new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                transView.setDuration((int) changeBounds.getDuration());
                transView.startTransWithOutRotate();
            }
        });
        getWindow().setSharedElementEnterTransition(changeBounds);
        transView = (TransPathView) findViewById(R.id.trans_path_view);
        transView.setPaths(getString(R.string.heart), getString(R.string.twitter));
        transView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
      super.onBackPressed();
    }


}