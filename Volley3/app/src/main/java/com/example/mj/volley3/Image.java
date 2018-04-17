package com.example.mj.volley3;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class Image extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        new VImages().execute(url);


    }


    private class VImages extends AsyncTask<String ,Void, String> {
        @Override
        protected String doInBackground(String... strings) {


            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest("http://placehold.it/600/771796",
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {

                           int height= bitmap.getHeight();
                           int width = bitmap.getWidth();
                            int size = height*width;
                            int sw = getResources().getConfiguration().smallestScreenWidthDp;

                            float px = 600;
                            float dp = px / getResources().getDisplayMetrics().density;
                            int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap);

                            long fileSizeInKB = bitmapByteCount / 1024;

                            Log.d(TAG, "onResponse: "+bitmapByteCount+"\n");
                            Log.d(TAG, "onResponse: "+ height+"x"+width+"\n Size "+fileSizeInKB+"kb");
                        }


                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "onErrorResponse: "+error.toString());
                        }
                    });
// Access the RequestQueue through your singleton class.
            //MySingleton.getInstance(this).addToRequestQueue(request);


            queue.add(request);


            return String.valueOf(queue);
        }
    }
}
