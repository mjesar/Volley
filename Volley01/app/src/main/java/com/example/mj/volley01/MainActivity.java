package com.example.mj.volley01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    EditText etUsername,etPassword;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etUsername= (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        bLogin =(Button)findViewById(R.id.bLogin);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url1 = "http://10.0.2.2/customers/test.php";

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        /*if (response.contains("correct")) {
                            Intent intent = new Intent(MainActivity.this,SubActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Incorrect UserName or passeord", Toast.LENGTH_SHORT).show();
                        }*/
                        Log.d(TAG,response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof com.android.volley.TimeoutError ){
                            Toast.makeText(getApplicationContext(), "TimeOutError", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof com.android.volley.NoConnectionError){
                            Toast.makeText(getApplicationContext(), "NoConnectionError", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof com.android.volley.AuthFailureError){
                            Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof com.android.volley.NetworkError){
                            Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof com.android.volley.ParseError){
                            Toast.makeText(getApplicationContext(), "Jason Parse Error", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof com.android.volley.ServerError){
                            Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_SHORT).show();
                        }

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params= new HashMap<>();
                        params.put("username",etUsername.getText().toString());
                        params.put("password",etPassword.getText().toString());

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headers =new HashMap<>();
                        headers.put("User-Agent","MyAndroid");
                        headers.put("Accept-language","eng");

                        return headers;
                    }
                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest1);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
