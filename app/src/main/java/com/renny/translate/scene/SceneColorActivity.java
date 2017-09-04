package com.renny.translate.scene;

import android.os.Bundle;
import android.transition.Transition;

import com.renny.translate.R;
import com.renny.translate.transition.ChangeColor;

public class SceneColorActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_color);
        initScene(R.id.scene_root,R.layout.scene_1_color,R.layout.scene_2_color);
    }

    @Override
    Transition getTransition() {
        return new ChangeColor();
    }
}
