package com.example.mj.volley02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity implements Response.Listener<String> {

    final String TAG = this.getClass().getSimpleName();
    ListView lvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
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

        String url = "http://10.0.2.2/customer/product.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while reading Data", Toast.LENGTH_SHORT).show();
            }
        });
         MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);
        ArrayList<Product> productList = new JsonConverter<Product>().toArrayList(response, Product.class);

        BindDictionary<Product> dictionary = new BindDictionary<>();
        dictionary.addStringField(R.id.tvText, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return product.name;
            }
        });
        dictionary.addDynamicImageField(R.id.ivImage, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return product.image_url;
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView imageView) {

                Picasso.with(getApplicationContext()).load("http://10.0.2.2/customer/" + url).into(imageView);
                imageView.setPadding(0, 0, 0, 0);
                imageView.setAdjustViewBounds(true);
            }
        });

        FunDapter<Product> adapter = new FunDapter<>(getApplicationContext(),
                productList, R.layout.product_layout, dictionary);

        lvProduct.setAdapter(adapter);

    }

}
