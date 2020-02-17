package com.khoa.datetimepicker;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimePickerDialog extends Dialog {

    private TextView txtTime;
    private RecyclerView rvDate;
    private RecyclerView rvHour;
    private RecyclerView rvMin;

    private Date date;
    private Date tempDate;
    private DateTimePickerCallBack callBack;
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private SelectableScrolling scrollingDate;
    private SelectableScrolling scrollingHour;
    private SelectableScrolling scrollingMin;

    public DateTimePickerDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.date_time_picker_dialog);
        txtTime = findViewById(R.id.txt_time);
        rvDate = findViewById(R.id.rv_date);
        rvHour = findViewById(R.id.rv_hour);
        rvMin = findViewById(R.id.rv_min);

//        Window window = getWindow();
//        window.setGravity(Gravity.BOTTOM);

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancel();
            }
        });

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOk();
            }
        });
    }

    public void showDateTimePickerDialog(Date date, DateTimePickerCallBack callBack) {
        this.date = date;
        this.callBack = callBack;
        this.tempDate = (Date) date.clone();
        setupRecyclerViewDate();
        setupRecyclerViewHour();
        setupRecyclerViewMin();
        show();
    }

    private void setupRecyclerViewDate() {
        rvDate.setAdapter(new DateRecyclerViewAdapter(getContext(), date));
        scrollingDate = new SelectableScrolling(rvDate, new OnItemChangeListener() {
            @Override
            public void onItemChanged(int position) {
                tempDate = getDateFromPosition(date, position);
                txtTime.setText(df.format(tempDate));
            }
        });
        rvDate.addOnScrollListener(scrollingDate);
        rvDate.scrollToPosition(DateRecyclerViewAdapter.FISRT_POSITION);
        rvDate.post(new Runnable() {
            @Override
            public void run() {
                scrollingDate.setSelectedItem(SelectableScrolling.primaryPosition);
            }
        });
    }

    private void setupRecyclerViewHour() {
        HourRecyclerViewAdapter adapter = new HourRecyclerViewAdapter(getContext());
        rvHour.setAdapter(adapter);
        scrollingHour = new SelectableScrolling(rvHour, new OnItemChangeListener() {
            @Override
            public void onItemChanged(int position) {
                int hour = convertToHour(position);
                tempDate.setHours(hour);
                txtTime.setText(df.format(tempDate));
            }
        });
        rvHour.addOnScrollListener(scrollingHour);
        rvHour.scrollToPosition(findAdapterPositionHour() - 2);
        rvHour.post(new Runnable() {
            @Override
            public void run() {
                scrollingHour.setSelectedItem(SelectableScrolling.primaryPosition);
            }
        });
    }

    private void setupRecyclerViewMin() {
        MinRecyclerViewAdapter adapter = new MinRecyclerViewAdapter(getContext());
        rvMin.setAdapter(adapter);
        scrollingMin = new SelectableScrolling(rvMin, new OnItemChangeListener() {
            @Override
            public void onItemChanged(int position) {
                int min = convertToMin(position);
                tempDate.setMinutes(min);
                txtTime.setText(df.format(tempDate));
            }
        });
        rvMin.addOnScrollListener(scrollingMin);
        rvMin.scrollToPosition(findAdapterPositionMin() - 2);
        rvMin.post(new Runnable() {
            @Override
            public void run() {
                scrollingMin.setSelectedItem(SelectableScrolling.primaryPosition);
            }
        });
    }

    private Date getDateFromPosition(Date fromDate, int toPosition) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.DAY_OF_MONTH, toPosition - DateRecyclerViewAdapter.FISRT_POSITION);

        Date newDate = calendar.getTime();
        newDate.setHours(tempDate.getHours());
        newDate.setMinutes(tempDate.getMinutes());
        return newDate;
    }

    private int findAdapterPositionHour() {
        int hour = date.getHours();
        return hour + 2;
    }

    private int convertToHour(int position) {
        return position - 2;
    }

    private int findAdapterPositionMin() {
        int min = Math.round((float) date.getMinutes() / 5);
        return min + 2;
    }

    private int convertToMin(int position) {
        return (position - 2) * 5;
    }

    private void onClickCancel() {
        dismiss();
    }

    private void onClickOk() {
        callBack.onDateTimePicked(tempDate);
        dismiss();
    }
}
