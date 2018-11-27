package com.example.klechak.hamradiotools2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    private Button btn_to_logger;
    private Button btn_to_viewlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        ImageButton buttonParse = findViewById(R.id.button_parse);

        btn_to_viewlog = (Button) findViewById(R.id.btn_to_viewlog);
        btn_to_viewlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Viewer();  // send to the method below.  11/18/2018.
            }
        });


//        EditText newcall = (EditText) findViewById(R.id.enterCall_Txt);

        mQueue = Volley.newRequestQueue(this);


        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParse();


            }
        });
    }

    private void jsonParse() {
        EditText newcall = (EditText) findViewById(R.id.enterCall_Txt);
        String stringCall = newcall.getText().toString();
        String url = "https://callook.info/index.php?callsign=" + stringCall + "&display=json";
        // 11/15/19 - 1140 - testing with fcc array; - https://data.fcc.gov/api/license-view/basicSearch/getLicenses?searchValue=w9fff&format=json&jsonCallback=?
        // "https://callook.info/index.php?callsign=w9fff&display=json json format

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject result = response;
                        try {
                            String status = result.getString("status");
                            String type = result.getString("type");

                            JSONObject current = result.getJSONObject("current");
                            String callsign = current.getString("callsign");
                            String operClass = current.getString("operClass");

                            JSONObject previous = result.getJSONObject("previous");
                            String previousCallsign = previous.getString("callsign");
                            String previousOperclass = previous.getString("operClass");

                            JSONObject trustee = result.getJSONObject("trustee");
                            String trusteeCallsign = trustee.getString("callsign");
                            String trusteeName = trustee.getString("name");

                            String name = result.getString("name");

                            JSONObject address = result.getJSONObject("address");
                            String line1 = address.getString("line1");
                            String line2 = address.getString("line2");
                            String attn = address.getString("attn");

                            JSONObject location = result.getJSONObject("location");
                            String latitude = location.getString("latitude");
                            String longitude = location.getString("longitude");
                            String gridsquare = location.getString("gridsquare");

                            JSONObject otherInfo = result.getJSONObject("otherInfo");
                            String grantDate = otherInfo.getString("grantDate");
                            String expiryDate = otherInfo.getString("expiryDate");
                            String frn = otherInfo.getString("frn");
                            String ulsUrl = otherInfo.getString("ulsUrl");

                            if (callsign.equals("N9VU")) {
                                mTextViewResult.setText("Gary Bernstein is a Repeater Jammer! ");

                            } else {
                                mTextViewResult.setText("Information for " + callsign + "\n\n" + name + "\n" + line1 + "\n" + line2 + "\n\nLicense Status: " + status + "\nLicense Class: " + operClass +
                                        "\n\nExpiration Date: " + expiryDate + "\nPrevious Class License: " + previousOperclass + "\nPrevious Callsign: " + previousCallsign +
                                        "\nFRN: " + frn + "\n\nAdditional Information: \n\n" + ulsUrl);
                                // UPDATE 11/16/2018 - Finally getting Somewhere - n: Value W9FFF at callsign of type java.lang.String cannot be converted to JSONObject
                                // making String Callsign resultcurrent.getString("CallSign") for testing purposes.

                                btn_to_logger = (Button) findViewById(R.id.btn_to_logger);  // placed here so no button can  be clicked if there is no valid information obtained.
                                btn_to_logger.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Logger();  // send to the method below.  11/18/2018.
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mTextViewResult.setText("The Callsign is invalid");
                        }


//
//                        String callsign = null;
//                        try {
//
//                            callsign = current.getString("callsign");
//                        }catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                                //JSONArray current = new JSONArray(result);
//                                // A lot going on here. define jsonArray.  Get the current level of the
//                                // json array.  read the full array (current) until there is no more entries available
//                                // in ,my case this SHOULD be "callsign" and operClass".   Below, if there is an error,
//                                // catch the error.
//
////                                String callsign = current.getString("licName");
////                                String operClass = current.getString("operClass");


                        // W/System.err: org.json.JSONException: Value {"callsign":"W9FFF","operClass":"GENERAL"} at current of type org.json.JSONObject cannot be converted to JSONArray
                        // discovered.... My json file is a single object, not an array. This results in the error above.
                        //                                // to fix, i need to learn how to make android see it as an object, not a jsonArray.
                        // a solution may be: https://stackoverflow.com/questions/10164741/get-jsonarray-without-array-name
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); // just to see what happens on error.
            } // Two listeners above.  The first one responds with the data, when the request is
            // successful and the second one occurs when there is an error.
        });

        mQueue.add(request);

        // Calling the request...

        // First Run: ERROR - 11/15/2018 - @ 1055hours
        //Program type already present: com.android.volley.ExecutorDelivery
        //Message{kind=ERROR, text=Program type already present: com.android.volley.ExecutorDelivery, sources=[Unknown source file], tool name=Optional.of(D8)}
    }

    public void Logger() {
        EditText editText = findViewById(R.id.enterCall_Txt);
        String callsign = editText.getText().toString();

        Intent intent = new Intent(this, Logger.class);
        intent.putExtra("callsign", callsign);
//        String callsign = callsign.getText().toString;   // NOTE, eventually i'll implement the importing of the city and state and other json data SK 11/18/2018 1330.
//        intent.putExtra("callsign", callsign);
        startActivity(intent);
    }
    public void Viewer() {

        Intent intent = new Intent(this, Viewer.class);
        startActivity(intent);
    }
}
