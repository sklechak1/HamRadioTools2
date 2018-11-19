package com.example.klechak.hamradiotools2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class Logger extends AppCompatActivity {
    private TextView textViewlogger;
    private TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        Calendar calendar = Calendar.getInstance();
        String curDate = DateFormat.getDateTimeInstance().format(calendar.getTime());


        Bundle bundle = getIntent().getExtras();
        String callsign = bundle.getString("callsign");

        textViewlogger = findViewById(R.id.callLogged);
        textViewlogger.setText(callsign);

        textViewDate = findViewById(R.id.txtViewDisplayDate);
        textViewDate.setText(curDate);
    }
}
