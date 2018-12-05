package com.example.klechak.hamradiotools2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;

public class DxSpots extends AppCompatActivity {
    private TextView info;
//    TextView info = (TextView) findViewById(R.id.txt1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dx_spots);
//        TextView info = (TextView) findViewById(R.id.txt1);
         info = (TextView)findViewById(R.id.txt2);
        new parse().execute();
    }

    public class parse extends AsyncTask<String, Void, String> {
        String dataget;
        String datarows;
        String ss;
        private static final String FILE_NAME = "dxspots.txt";
        FileOutputStream callFO = null;
        StringBuffer buffer = new StringBuffer();


        @Override
        protected String doInBackground(String... strings) {
//
//            TextView info = (TextView) findViewById(R.id.txt1);
            try {
                Document doc = Jsoup.connect("https://www.qrz.com/dxcluster").get();
                Elements tables = doc.select("table");
                Element table = tables.get(0);

                while (!table.select(":has(table)").isEmpty()) {
                    table = table.select("table table").first();
//                    System.out.println(table);

                    Elements rows = table.select("tr");
                    for (Element row : rows) {
                        Elements columns = row.select("tr");
                        StringBuilder output = new StringBuilder();
                        for (Element column : columns) {

                            output.append(column.text());
                            // THIS IS THE KEY .. append colum text to string.
//                            System.out.println();
                            System.out.print(column.text());
//                            ss = output.toString();
                            buffer.append(output + "\n\n");




                        }
                        System.out.println();
//                        info.setText(output.toString());
//                          info.setText(ss);
//                        FileOutputStream callFO = null;



                    }


                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return buffer.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            info.setText(s);
            info.setMovementMethod(new ScrollingMovementMethod());


        }


    }

}