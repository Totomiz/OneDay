package com.zt.android.oneday;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcl on 2018/2/12.
 */

public class OneDay extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter mAdp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdp=new Adapter(query());
        mRecyclerView.setAdapter(mAdp);
    }

    private static class IntentBean{
        private String label;
        private Intent intent;
    }

    private List<IntentBean> query(){
        List<IntentBean> beanList=new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("com.zt.android.TEST_CODE");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
        for (ResolveInfo resolveInfo : list) {
            CharSequence labelSeq = resolveInfo.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : resolveInfo.activityInfo.name;
            resolveInfo.activityInfo.getClass();
            IntentBean bean=new IntentBean();
            bean.label=label;
            bean.intent=activityIntent(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name);
            beanList.add(bean);
        }

        return beanList;
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    private class Adapter extends RecyclerView.Adapter<Item>{
        private List<IntentBean> mData;

        public Adapter(List<IntentBean> data){
            this.mData=data;
        }
        @Override
        public Item onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);

            return new Item(inflate);
        }

        @Override
        public void onBindViewHolder(Item holder, int position) {
            holder.bindData(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class Item<T> extends RecyclerView.ViewHolder{
        private TextView textView;
        public Item(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView);

        }

        public void bindData(final IntentBean bean){
            textView.setText(bean.label);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(bean.intent);
                }
            });
        }
    }
}
