package com.renny.translate.shape;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.renny.translate.R;
import com.renny.translate.widget.TransPathView;


public class CustomSVGTransitionFromActivity extends AppCompatActivity
        implements View.OnClickListener {

    TransPathView transView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_svg_from);
        transView = (TransPathView) findViewById(R.id.trans_path_view);
        transView.setPaths(getString(R.string.heart), getString(R.string.twitter));
        transView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CustomSVGTransitionToActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                , new Pair<View, String>(transView, "shared_transView_"));
        startActivity(intent, activityOptionsCompat.toBundle());

    }


}