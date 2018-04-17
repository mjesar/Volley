package com.example.mj.volley3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Post extends AppCompatActivity {
    private static final String TAG = Post.class.getSimpleName();
    private String jsonResponse;
    String url = "https://jsonplaceholder.typicode.com/posts";
    TextView textView1;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        queue = Volley.newRequestQueue(this);
        textView1= (TextView)findViewById(R.id.textView1);

//        Log.d(TAG, "onCreate:sad "+pojo.getId());
        new PostData().execute();

    }

    class PostData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {


            /* pojo = new MyPojo();
            pojo.setUserID("1");
            pojo.setId("101");
            pojo.getTitle("LKKLK");
            pojo.setBody("KSJDLKjlksdjflksdj salkdjlaskdj;lsad");
            Log.d(TAG, "doInBackgroundasdaa: " + pojo);
          JSONObject jsonObj = new JSONObject(params);*/

            String posturl = "http://10.0.2.2:3000/users";

            StringRequest myRequest = new StringRequest(Request.Method.POST, posturl,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, "onResponse: "+response);
                    textView1.setText(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: "+error.toString());

                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("userId", String.valueOf(10));
                    params.put("id", "11111");
                    params.put("title", "th title");
                    params.put("body", "th title");
                    Log.d(TAG, "getParams: "+params);
                    return params;
                }
            };
            queue.add(myRequest);
            Log.d(TAG, "doInBackgroundsadaaa: "+queue.toString());
            return String.valueOf(queue);


        }
    }
}
