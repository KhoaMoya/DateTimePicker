package com.khoa.datetimepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;

public class DateRecyclerViewAdapter extends RecyclerView.Adapter<DateRecyclerViewAdapter.ViewHolder> {

    public final static int FISRT_POSITION = 90;
    private Calendar calendar;
    private Context context;
    private Date mDate;

    public DateRecyclerViewAdapter(Context context, Date mDate) {
        this.context = context;
        this.mDate = mDate;
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(mDate);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        int position = holder.getAdapterPosition();
        int subPosition = position - FISRT_POSITION;
        calendar.add(Calendar.DAY_OF_MONTH, subPosition);
        Date date = calendar.getTime();
        holder.bind(date);

        restartCalendar();
    }

    private void restartCalendar(){
        calendar.setTime(mDate);
    }

    @Override
    public int getItemCount() {
        return 200;
    }

    public class ViewHolder extends MyViewHolder {
        public Date date;

        public ViewHolder(@NonNull View itemView) {
            super(context, itemView);
            textView = itemView.findViewById(R.id.txt);
        }

        public void bind(Date date) {
            this.date = date;
            int day = date.getDate();
            int month = date.getMonth() + 1;
            int year = date.getYear() + 1900;

            textView.setText(day + " tháng " + month);
        }

    }
}
