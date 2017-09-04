package com.renny.translate.scene;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;

import com.renny.translate.R;

public class SceneBerNoActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_ber);
        initToolbar();
        initScene(R.id.scene_root,R.layout.scene_1_ber,R.layout.scene_2_ber);
    }

    @Override
    Transition getTransition() {
        return new ChangeBounds();
    }
}
