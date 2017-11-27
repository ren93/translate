package com.renny.translate.transitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Visibility;

import com.renny.translate.R;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Visibility explode = new Explode();
        explode.setDuration(1000);
        explode.addTarget(R.id.toolBar)
                .addTarget(R.id.bottom_tab);
        getWindow().setEnterTransition(explode);
    }
}
