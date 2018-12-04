package com.example.klechak.hamradiotools2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DxSpots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//         info = (TextView)findViewById(R.id.txt1);
//        TextView info = (TextView) findViewById(R.id.txt1);
        new parse().execute();
    }

    public class parse extends AsyncTask<Void, Void, Void> {
        String dataget;
        String datarows;
        String ss;

        @Override
        protected Void doInBackground(Void... voids) {
//
            TextView info = (TextView) findViewById(R.id.txt1);
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
//                            System.out.println();
                            System.out.print(column.text());
////                            ss = columns.text();



                        }
                        System.out.println();
//                        info.setText(output.toString());
//                        info.setText("Hello");


                    }


                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }


    }

}