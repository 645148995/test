package com.sankuai.meituan.test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final XXXLayout xxxLayout = findViewById(R.id.xxxxxx);
        LinearLayoutManager tManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(tManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int totalY = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalY += dy;
                int maxHeight = DisplayUtils.dp2px(recyclerView.getContext(), 50);
                xxxLayout.set(maxHeight, totalY);
                Log.e("duanchao", "totalY:" + totalY + ";maxHeight:" + maxHeight);
            }
        });
        recyclerView.setAdapter(new Adapter());
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View view;
            view = new View(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dp2px(context, 200));
            view.setLayoutParams(params);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (position == 0) {
                holder.itemView.setBackgroundResource(R.drawable.shape_bg_button_reply);
            } else if (position % 2 == 0) {
                holder.itemView.setBackgroundResource(android.R.color.holo_red_dark);
            } else {
                holder.itemView.setBackgroundResource(android.R.color.holo_blue_bright);
            }
        }


        @Override
        public int getItemCount() {
            return 50;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}