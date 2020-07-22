package com.zt.android.oneday.base.context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 2017/12/27.
 *
 */

public abstract class BaseButtonListActivity extends AppCompatActivity{
    List<String> datas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView=new RecyclerView(this);
        setContentView(recyclerView);
//        datas=setButtonList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Adapter adapter = new Adapter(datas);
        recyclerView.setAdapter(adapter);
    }



//    public abstract List<String> setButtonList(List){};


    private static class Adapter extends RecyclerView.Adapter<Holder>{
        private List<String> data;
        private OnClickCall onClickCall;

        public void setOnClickCall(OnClickCall clickCall){
            this.onClickCall=clickCall;
        }


        public Adapter(List<String> data) {
            this.data = data;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new Holder(inflate);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bindData(data.get(position));
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickCall!=null){
                        onClickCall.onClick("");
                    }
                }
            });
        }

        public int getItemCount() {
            return data.size();
        }
    }
    public static interface OnClickCall{
        public void onClick(String s);
    }
    private static class Holder extends RecyclerView.ViewHolder{
        private TextView textView;
        private View root;
        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            root=itemView;
        }

        public void bindData(String s){
            textView.setHint(s);
        }
    }
}
