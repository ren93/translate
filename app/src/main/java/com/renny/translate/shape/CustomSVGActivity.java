package com.renny.translate.shape;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import com.renny.translate.R;
import com.renny.translate.widget.TransPathView;



public class CustomSVGActivity extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    TransPathView transView;

    SeekBar seekBar;

    public static final String   HEART = "M99,349 C193,240,283,165,400,99 C525,172,611,246,701,348 C521,416,433,511,400,700 C356,509,285,416,99,349";
    public static final String TWITTER = "M99,349 C297,346,376,210,400,99 C432,208,506,345,701,348 C629,479,549,570,400,700 C227,569,194,522,99,349";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_svg);
        transView = (TransPathView)findViewById(R.id.trans_path_view);
        transView.setViewPort(1600, 1600);
        transView.setPaths(HEART, TWITTER);
        transView.setOnClickListener(this);

        seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        transView.startTransWithOutRotate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float fraction = progress * 1.0f / seekBar.getMax();
        transView.setFraction(fraction);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}