package com.example.klechak.hamradiotools2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class Logger extends AppCompatActivity {
    private TextView textViewlogger;
    private TextView textViewDate;
    private TextView txtPower;
    private TextView txtFreq;
    private TextView txtComments;

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
        String logDate = curDate;


        txtPower = findViewById(R.id.txtPower);
        String stringPower = txtPower.getText().toString();

        txtFreq = findViewById(R.id.txtFreq);
        String stringFreq = txtFreq.getText().toString();

        txtComments = findViewById(R.id.txtComments);
        String stringComments = txtComments.getText().toString();


        Button btnSubmitLog = findViewById(R.id.btnSubmitLog);


        btnSubmitLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogContact();


            }
        });
    }

    private void LogContact() {
        final RadioGroup radGpMode = (RadioGroup) findViewById(R.id.rgMode);


        Bundle bundle = getIntent().getExtras();
        String callsign = bundle.getString("callsign");

        Calendar calendar = Calendar.getInstance();
        String curDate = DateFormat.getDateTimeInstance().format(calendar.getTime());
        String stringFreq = txtFreq.getText().toString();
        String stringPower = txtPower.getText().toString();
        String stringComments = txtComments.getText().toString();
        String stringMode;

        int selectedMode = radGpMode.getCheckedRadioButtonId();
            if (selectedMode == 1){
                stringMode = "USB";
            }else if (selectedMode == 2){
                stringMode = "LSB";
            }else if (selectedMode == 3) {
                stringMode = "AM";
            }else if (selectedMode == 4) {
                stringMode = "FM";
            }else{
                stringMode = "Unknown Mode";
            }


            













    }

}
