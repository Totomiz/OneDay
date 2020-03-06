package com.zt.android.oneday.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.zt.android.oneday.R;
import com.zt.android.oneday.base.context.ScheduledTask;

import java.util.ArrayList;
import java.util.List;

public class AnimationItemActivity extends AppCompatActivity {
    
    private static final String TAG= AnimationItemActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private Button btn;
    private List<String> mData;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btn = (Button) findViewById(R.id.button);

        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = "# data " + i;
            mData.add(s);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);
        dataAdapter = new DataAdapter(mData);
        recyclerView.setAdapter(dataAdapter);
        ScheduledTask.getInstance().executeTask();

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView viewById = (TextView) findViewById(R.id.textView);
        CharSequence text = viewById.getText();
        Log.d(TAG, "onResume: text  ==="+text);
        if(text.equals("aaa")){
            Log.d(TAG, "onResume: dsf");
        }else{
            Log.d(TAG, "onResume: cuowu ");
        }

        viewById.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: this delay message");
            }
        },7000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        finish();
    }

    public void onClick(View view) {
        mData.add(mData.size(), "## data" + mData.size());
        dataAdapter.notifyItemInserted(3);
        getSharedPreferences("adlibrary", Context.MODE_PRIVATE).edit().putString("HOMEPAGE_ID", null);
    }

    public void onClick2(View view) {
        String s=getSharedPreferences("adlibrary", Context.MODE_PRIVATE).getString("HOMEPAGE_ID", "aaa");
        Log.d(TAG, "onClick2: "+s);
//        mData.remove(3);
        dataAdapter.reset();
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private static class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder> {
        private List<String> data;
        private boolean isFirst=true;
        private int mLastPosition=-1;

        public void reset(){
            mLastPosition=-1;
        }

        public DataAdapter(List<String> data) {
            this.data = data;
        }

        public Animator[] getAnimators(View view) {
//            return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", 0, 1f)};
            return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0)};
        }

        public void setFirst(boolean first){
            this.isFirst=first;
        }


        @Override
        public void onViewAttachedToWindow(Holder holder) {
            super.onViewAttachedToWindow(holder);
            Log.d(TAG, "onViewAttachedToWindow: "+holder.getLayoutPosition());
            if(!isFirst||holder.getLayoutPosition()>mLastPosition){
                Animator[] animators = getAnimators(holder.itemView);
                for (Animator animator : animators) {
                    startAnimation(animator, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }

        }

//        @Override
//        public void onViewDetachedFromWindow(Holder holder) {
//            super.onViewDetachedFromWindow(holder);
//            Log.d(TAG, "onViewDetachedFromWindow: "+holder.getLayoutPosition());
//            holder.itemView.setAnimation(null);
//
//        }

        public void startAnimation(Animator animators, int position) {
            Log.d(TAG, "startAnimation() called with: animators = [" + animators + "], position = [" + position + "]");
            animators.setDuration(1500).setStartDelay((long) (100 * position));
            animators.setInterpolator(new LinearInterpolator());
            animators.start();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new Holder(inflate);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(data.get(position));
        }

        public static class Holder extends RecyclerView.ViewHolder {
            TextView textView;

            public Holder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}



