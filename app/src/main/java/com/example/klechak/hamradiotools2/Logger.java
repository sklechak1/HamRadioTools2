package com.example.klechak.hamradiotools2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class Logger extends AppCompatActivity {
    private TextView textViewlogger;
    private TextView textViewDate;
    private TextView txtPower;
    private TextView txtFreq;
    private TextView txtComments;
    private static final String FILE_NAME = "logs.txt";

    RadioGroup radioGroup;
    String stringMode = "";

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
//        String btnsubmit = btnSubmitLog.getText().toString();

        btnSubmitLog.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                LogContact();


            }
        });

        final RadioGroup radioGroup = findViewById(R.id.rgMode);
        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedMode = radioGroup.getCheckedRadioButtonId();
                switch(selectedMode) {
                    case R.id.modebtnUSB: {
                        stringMode = "USB";
                        break;
                    }
                    case R.id.modebtnLSB: {
                        stringMode = "LSB";
                        break;
                    }
                    case R.id.modebtnAM: {
                        stringMode = "AM";
                        break;
                    }
                    case R.id.modebtnFM: {
                        stringMode = "FM";
                        break;
                    }
                }


            }

        });
    }


    public void LogContact() {



        Bundle bundle = getIntent().getExtras();
        String callsign = bundle.getString("callsign");

        Calendar calendar = Calendar.getInstance();
        String curDate = DateFormat.getDateTimeInstance().format(calendar.getTime());
        String stringFreq = txtFreq.getText().toString();
        String stringPower = txtPower.getText().toString();
        String stringComments = txtComments.getText().toString();
        String outputString;




        outputString = callsign + " - " + curDate + " - " +  " - " + stringFreq + " - " + stringPower + " Watts  - " + stringMode + " " + stringComments + "\n__________________________________________________\n"; // i bet \n doesn't do anything Haha.

        FileOutputStream callFO = null;
        try {
            callFO = openFileOutput(FILE_NAME, MODE_APPEND);
            callFO.write(outputString.getBytes());
            // NOTE TO ME - 11/19/2018 - I need to add .clear to the fields once everthing sucessfully saves
            // txtPower.getText().clear();
            Toast.makeText(this,"Contact Saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (callFO != null){
                try {
                    callFO.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
