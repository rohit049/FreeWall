package com.example.freewall.api;

import android.content.Context;
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


public class WallpaperData {

  public interface TransactionComplete {
    void onNotifyAdapter();

    void addItemToArrayList(WallpaperItem item);
  }

  private TransactionComplete listener;
  private static final String TAG = "FetchData";
  RequestQueue mRequestQueue;
  private WallpaperAdapter wallpaperAdapter;
  private static WallpaperData uniqInstance;


  public static WallpaperData getInstance() {
    if (uniqInstance == null)
      uniqInstance = new WallpaperData();
    return uniqInstance;
  }

  public void onFetchData(String URL, Context context, TransactionComplete instance) {
    listener = instance;
    mRequestQueue = Volley.newRequestQueue(context);

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
      Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        //Send data to fragments
        if (listener == null) {
          Log.d(TAG, "FetchData: onResponse: Listener is Null");
        } else {
          parseData(response);
          listener.onNotifyAdapter();
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
  

  private void parseData(JSONObject object) {
    try {
      JSONArray walls = object.getJSONObject("data").getJSONArray("walls");

      for (int i = 0; i < walls.length(); i++) {
        JSONObject wall = walls.getJSONObject(i);
        WallpaperItem wallpaperItem = new WallpaperItem(
          wall.getString("name"),
          wall.getString("urls"),
          wall.getInt("likes"));

        listener.addItemToArrayList(wallpaperItem);
//        Log.d(TAG, "Name: " + wallpaperItem.getName());

      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
