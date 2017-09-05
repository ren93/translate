package com.renny.translate.scene;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;

import com.renny.translate.R;


public class SceneChangeBoundsLayoutActivity extends BaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_change_bounds);
        initToolbar();
        initScene(R.id.scene_root,R.layout.scene_1_changebounds_layout,R.layout.scene_2_changebounds);
    }

    @Override
    Transition getTransition() {
        return new ChangeBounds();
    }


}
