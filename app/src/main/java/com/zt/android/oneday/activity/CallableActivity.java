package com.zt.android.oneday.activity;

import android.util.Log;
import android.view.View;

import com.zt.android.oneday.base.context.BaseActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by tcl on 2018/2/12.
 */

public class CallableActivity extends BaseActivity{
    private static final String TAG = "CallableActivity";
    @Override
    public void onClick(View view) {
        super.onClick(view);
        Log.d(TAG, "onClick: ");
//        FutureTask<>
//        Thread thread=new Thread()

    }

    private static class TestCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            return null;
        }
    }
}
