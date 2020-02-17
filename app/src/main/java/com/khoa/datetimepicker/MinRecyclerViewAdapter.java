package com.khoa.datetimepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class MinRecyclerViewAdapter extends RecyclerView.Adapter<MinRecyclerViewAdapter.ViewHolder> {

    private Context context;

    public MinRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    // --0 5 10 15 20 25 30 35 40 45 50 55 --
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        int position = holder.getAdapterPosition();
        if(position<=1 || position>=14){
            holder.bind("");
        } else {
            int min = (position-2) * 5;
            holder.bind(String.format("%02d", min));
        }
    }

    @Override
    public int getItemCount() {
        return 16;
    }

    public class ViewHolder extends MyViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(context, itemView);
            textView = itemView.findViewById(R.id.txt);
        }

        private void bind(String min) {
            textView.setText(min);
        }
    }
}
