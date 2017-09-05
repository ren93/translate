package com.renny.translate.transitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.widget.LinearLayout;

import com.renny.translate.R;

public class WithSharedElementTransitionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout liney_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode().setDuration(3000));
        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(3000));
        setContentView(R.layout.activity_with_shared_element_transitions);
        initView();
        initToolbar();
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
