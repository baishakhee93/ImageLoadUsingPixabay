package com.shakhee.imageloadusingpixabay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.shakhee.imageloadusingpixabay.adapter.ImageListAdapter;
import com.shakhee.imageloadusingpixabay.api.Constant;
import com.shakhee.imageloadusingpixabay.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static RecyclerView mRecyclerView;
    @SuppressLint("StaticFieldLeak")
    public static Activity MA;
    private List<Item> mList;
    @SuppressLint("StaticFieldLeak")
    public static RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    public static ImageListAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MA=this;
        mRecyclerView=findViewById(R.id.recylerView);
        requestQueue= Volley.newRequestQueue(MA);
        mList=new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // assigning ID of the toolbar to a variable
        MaterialToolbar toolbar =  findViewById(R.id.my_toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setSupportActionBar(toolbar);
        fetchData();

    }

    private void setSupportActionBar(Toolbar toolbar) {

    }


    private void fetchData() {

        String url = Constant.url;
System.out.println("sOrder.......url...."+url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("sOrder.......response...."+response);

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    System.out.println("sOrder.......jsonArray...."+jsonArray);
                    retrievingData(jsonArray);
                  /*  for(int i = 0 ; i<jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageUrl = jsonObject.getString("webformatURL");
                        int likes = jsonObject.getInt("likes");
                        String tags = jsonObject.getString("tags");

                        Item post = new Item(imageUrl , tags , likes);
                        mList.add(post);

                    }

                    ImageAdapter adapter = new ImageAdapter(MA , mList);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void retrievingData(JSONArray jsonArray) {
        try {
            mList = new ArrayList<>();
            if (jsonArray.length() > 0) {
                for (int s = 0; s < jsonArray.length(); s++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(s);
                    System.out.println("sOrder..........jsonObject..........."+jsonObject);
                    System.out.println("sOrder..........webformatURL..........."+jsonObject.getString("webformatURL"));
                    mList.add(new Item(jsonObject.getString("id"),jsonObject.getString("webformatURL"),jsonObject.getString("likes"),jsonObject.getString("tags"),jsonObject.getString("comments"),jsonObject.getString("webformatWidth"),jsonObject.getString("webformatHeight"),jsonObject.getString("views"),jsonObject.getString("downloads"),jsonObject.getString("collections"),jsonObject.getString("user_id"),jsonObject.getString("user"),jsonObject.getString("type")));
                }
              //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                 imageAdapter=new ImageListAdapter(MA,mList);
                mRecyclerView.setAdapter(imageAdapter);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}