package com.example.freewall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freewall.adapters.WallpaperAdapter;
import com.example.freewall.models.WallpaperItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WallpaperAdapter.OnWallListener {

  RequestQueue mRequestQueue;
  final String URL = "https://free-wall-paper.herokuapp.com/api/v1/walls";
  private static final String TAG = ".MainActivity";
  private ArrayList<WallpaperItem> mWallpapers;
  private RecyclerView mRecyclerView;
  private WallpaperAdapter mWallpaperAdapter;
  private Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRequestQueue = Volley.newRequestQueue(this);
    mWallpapers = new ArrayList<>();
    mRecyclerView = findViewById(R.id.recyclerview);
    mToolbar = findViewById(R.id.walls_toolbar);

    mToolbar.setTitle("FreeWall");
    mToolbar.setElevation(5);
    mToolbar.setTitleTextColor(getResources().getColor(R.color.colorText));

    initRecyclerView();
    getWallpaperData();
  }

  public void getWallpaperData() {

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
            mWallpapers.add(wallpaperItem);
            Log.d(TAG, "Name: " + wallpaperItem.getName());
          }
          mWallpaperAdapter.notifyDataSetChanged();
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

  private void initRecyclerView() {
    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    mWallpaperAdapter = new WallpaperAdapter(mWallpapers, this);
    mRecyclerView.setAdapter(mWallpaperAdapter);
  }

  @Override
  public void onWallClick(int position) {
    Log.d(TAG, "onWallClick: " + position);
    Intent intent = new Intent(this, ShowWallpaperActivity.class);

    intent.putExtra("selected_wallpaper", mWallpapers.get(position));
    startActivity(intent);
  }
}