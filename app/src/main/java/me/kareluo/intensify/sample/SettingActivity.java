package me.kareluo.intensify.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by felix on 16/7/17.
 */
public class SettingActivity extends AppCompatActivity {

    private Switch mAutoFix;

    private SeekBar mSpanCount;

    private RadioGroup mBlockType;

    private SeekBar mBlockSize;

    private SeekBar mHorizontalSpacing;

    private SeekBar mVerticalSpacing;

    private SeekBar mMaxLength;

    private SeekBar mMaxLines;

    private SeekBar mExtraCount;

    private RadioGroup mEllipseGravity;

    private RadioGroup mExtraSpacingGravity;

    private Switch mShowDivider;

    private Switch mShowSpacer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mAutoFix = (Switch) findViewById(R.id.sw_auto_fix);
        mAutoFix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingConfig.AUTO_FIT = isChecked;
            }
        });

        mSpanCount = (SeekBar) findViewById(R.id.sb_span_count);
        mSpanCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) SettingConfig.SPAN_COUNT = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBlockType = (RadioGroup) findViewById(R.id.rg_type);

        sync();
    }

    public void sync() {
        mAutoFix.setChecked(SettingConfig.AUTO_FIT);
        mSpanCount.setProgress(SettingConfig.SPAN_COUNT);
    }
}
