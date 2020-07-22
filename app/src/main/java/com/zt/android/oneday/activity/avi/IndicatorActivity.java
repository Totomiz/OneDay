package com.zt.android.oneday.activity.avi;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.zt.android.oneday.R;
import com.zt.android.oneday.widget.avi.AVLoadingIndicatorView;
import com.zt.android.utils.log.DebugLog;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class IndicatorActivity extends AppCompatActivity {

    private              AVLoadingIndicatorView avi;
    private static final String                 TAG = "IndicatorActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avi_indicator);

        String indicator = getIntent().getStringExtra("indicator");
        DebugLog.d(TAG, "onCreate() called with: indicator = [" + indicator + "]");

        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
    }

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}
