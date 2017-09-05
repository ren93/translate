package com.renny.translate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.renny.translate.scene.SceneBerActivity;
import com.renny.translate.scene.SceneBerNoActivity;
import com.renny.translate.scene.SceneChangeBoundsActivity;
import com.renny.translate.scene.SceneChangeBoundsLayoutActivity;
import com.renny.translate.scene.SceneColorActivity;
import com.renny.translate.transitions.TransitionsActivity;
import com.renny.translate.transitions.WithSharedElementTransitionsActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView shared_image;
    private TextView shared_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        shared_image = (ImageView) findViewById(R.id.shared_image);
        shared_text = (TextView) findViewById(R.id.shared_text);

        getWindow().setExitTransition(new Slide());
        Explode explode = new Explode();
        explode.setDuration(700);
        explode.setMode(Visibility.MODE_IN);
        explode.setInterpolator(new DecelerateInterpolator());
        getWindow().setReenterTransition(explode);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    public void jump(View view) {
        startActivity(new Intent(this, TransitionsActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void withShared(View view) {
        Intent intent = new Intent(this, WithSharedElementTransitionsActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                , new Pair<View, String>(shared_image, "shared_image_")
                , new Pair<View, String>(shared_text, "shared_text_"));
        startActivity(intent, activityOptionsCompat.toBundle());
    }

    public void sceneBer(View view) {
        start(SceneBerActivity.class);
    }

    public void sceneBerNo(View view) {
        start(SceneBerNoActivity.class);
    }

    public void sceneColor(View view) {
        start(SceneColorActivity.class);
    }

    public void scene(View view) {
        start(SceneChangeBoundsActivity.class);
    }

    public void sceneLayout(View view) {
        start(SceneChangeBoundsLayoutActivity.class);
    }

    public void start(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }


}
