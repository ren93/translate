package com.renny.translate.shape;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.renny.translate.R;
import com.renny.translate.listener.SimpleTransitionListener;


public class CustomSVGTransitionToActivity extends AppCompatActivity
        implements View.OnClickListener {

    ImageView transView;
    private boolean isTwitterChecked = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_svg_tra);
        transView = (ImageView) findViewById(R.id.trans_path_view);
        final ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(1000);
        changeBounds.addListener(new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                isTwitterChecked = !isTwitterChecked;
                final int[] stateSet = {android.R.attr.state_checked * (isTwitterChecked ? 1 : -1)};
                transView.setImageState(stateSet, true);
            }
        });
        getWindow().setSharedElementEnterTransition(changeBounds);
        transView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        isTwitterChecked = !isTwitterChecked;
        final int[] stateSet = {android.R.attr.state_checked * (isTwitterChecked ? 1 : -1)};
        transView.setImageState(stateSet, true);
      super.onBackPressed();
    }


}