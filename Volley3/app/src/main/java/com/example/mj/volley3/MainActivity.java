package com.example.mj.volley3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;
    TextView textView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          textView = (TextView)findViewById(R.id.text);
         queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        new ReciveData().execute(url);




    }

    class ReciveData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... objects) {
            String stringUrl = objects[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);


              JsonArrayRequest ArrayReq = new JsonArrayRequest(objects[0], new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                try {
                    jsonResponse = "";

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject person = (JSONObject) response
                                .get(i);


                        String userID = person.getString("userId");
                        String id = person.getString("id");
                        String title = person.getString("title");
                        String body = person.getString("body");

                        jsonResponse += "UserID : "+userID+"\n\n";
                        jsonResponse += "Id: "+id+"\n\n";
                        jsonResponse += "title: "+title+"\n\n";

                        jsonResponse += "Body: "+body+"\n\n";

                        Log.d(TAG, "onResponse: "+jsonResponse);
                        textView.setText(jsonResponse);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }
        );

        queue.add(ArrayReq);



                //Create a connection
               /* HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();*/
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }





            //Log.d(TAG, "doInBackground: "+queue.toString());
            return String.valueOf(queue);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
