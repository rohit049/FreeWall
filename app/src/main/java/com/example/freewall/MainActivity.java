package com.example.freewall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  RequestQueue mRequestQueue;
  final String URL = "https://free-wall-paper.herokuapp.com/api/v1/walls";
  private static final String TAG = ".MainActivity";
  //  TextView textView;
  ArrayList<WallpaperItem> wallpapers;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    textView = findViewById(R.id.textview_info);
    mRequestQueue = Volley.newRequestQueue(this);
    wallpapers = new ArrayList<WallpaperItem>();

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
        Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        try {

          JSONArray walls = response.getJSONObject("data").getJSONArray("walls");

          for (int i = 0; i < walls.length(); i++) {
            JSONObject wall = walls.getJSONObject(i);
            WallpaperItem wallpaperItem = new WallpaperItem(
                wall.getString("name"),
                wall.getString("urls"),
                wall.getInt("likes"));
            wallpapers.add(wallpaperItem);

            Log.d(TAG, "Name: " + wallpaperItem.getName());

            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(getApplicationContext(), wallpapers);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            recyclerView.setAdapter(wallpaperAdapter);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
      }
    });


    mRequestQueue.add(jsonObjectRequest);
  }
}