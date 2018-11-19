package com.example.klechak.hamradiotools2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Viewer extends AppCompatActivity {
    private static final String FILE_NAME = "log.txt";
    private TextView logView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        FileInputStream callFI = null;
        try {
            callFI = openFileInput(FILE_NAME);
            InputStreamReader reader = new InputStreamReader(callFI);
            BufferedReader br = new BufferedReader(reader);
            StringBuilder stringBuild = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null ){
                stringBuild.append(text).append("\n");
            }

           logView.setText(stringBuild.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (callFI != null){

            }
            try {
                callFI.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
