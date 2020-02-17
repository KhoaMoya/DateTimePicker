package com.khoa.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtSelectedDate = findViewById(R.id.txt_selected_date);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimePickerDialog dialog = new DateTimePickerDialog(MainActivity.this);
                dialog.showDateTimePickerDialog(new Date(), new DateTimePickerCallBack() {
                    @Override
                    public void onDateTimePicked(Date newDate) {
                        txtSelectedDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(newDate));
                    }
                });
            }
        });
    }

}
