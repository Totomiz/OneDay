package com.zt.android.oneday.base.context;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/1/17.
 *
 *
 * @author Tony zhang
 */

public class ScheduledTask {
    String TAG="ScheduledTask";

    private ScheduledThreadPoolExecutor mScheduledHandle;

    private ScheduledTask() {
        mScheduledHandle=new ScheduledThreadPoolExecutor(1);
    }

    public static ScheduledTask getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void executeTask(){
        mScheduledHandle.scheduleAtFixedRate(new TaskRunnable(),0,30, TimeUnit.SECONDS);
    }

    private static class SingletonHolder {
        private static final ScheduledTask INSTANCE = new ScheduledTask();
    }

    private class TaskRunnable implements Runnable{
        @Override
        public void run() {
            Log.d(TAG, "======="+new Date());
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            Log.d(TAG, "-------"+calendar.getTime());
        }
    }

}
