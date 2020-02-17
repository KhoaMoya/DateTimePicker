package com.khoa.datetimepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    private Context context;

    public MyViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
    }

    public void setNormal(){
        textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextSize(13);
    }

    public void setSelected(){
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        textView.setTextSize(17);
        textView.setTypeface(null, Typeface.BOLD);
    }
}
