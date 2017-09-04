package com.renny.translate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.renny.translate.scene.SceneBerActivity;
import com.renny.translate.scene.SceneBerNoActivity;
import com.renny.translate.scene.SceneChangeBoundsActivity;
import com.renny.translate.scene.SceneColorActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void jump(View view) {
        startActivity(new Intent(this, Main2Activity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
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

    public void start(Class<?> cls){
        startActivity(new Intent(this, cls));
    }


}
