package com.renny.translate.shape;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.renny.translate.R;


public class AnimatedVectorActivity extends AppCompatActivity {

    private ImageView imgBtn;
    private ImageView iv2;

    private boolean isTwitterChecked = false;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector);

        imgBtn = (ImageView) findViewById(R.id.imgBtn);
        iv2 = (ImageView) findViewById(R.id.iv_2);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startAnim(View view) {
        Drawable drawable = imgBtn.getDrawable();
        ((Animatable) drawable).start();
    }


    public void onTwitterClick(View view) {
        isTwitterChecked = !isTwitterChecked;
        final int[] stateSet = {android.R.attr.state_checked * (isTwitterChecked ? 1 : -1)};
        iv2.setImageState(stateSet, true);
    }


}
