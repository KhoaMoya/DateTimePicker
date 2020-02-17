package com.khoa.datetimepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class HourRecyclerViewAdapter extends RecyclerView.Adapter<HourRecyclerViewAdapter.ViewHolder> {

    private Context context;

    public HourRecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    //--012345678901234567890123--
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        int position = holder.getAdapterPosition();
        if(position<=1 || position>=26){
            holder.bind("");
        } else {
            holder.bind(position-2 + "");
        }
    }

    @Override
    public int getItemCount() {
        return 28;
    }

    public class ViewHolder extends MyViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(context, itemView);
            textView = itemView.findViewById(R.id.txt);
        }

        private void bind(String hour) {
            textView.setText(hour);
        }
    }
}
