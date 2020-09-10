package com.example.freewall;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {
  private Context mContext;
  private ArrayList<WallpaperItem> mData;


  public WallpaperAdapter(Context mContext, ArrayList<WallpaperItem> mData) {
    this.mContext = mContext;
    this.mData = mData;
  }

  @NonNull
  @Override
  public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;
    LayoutInflater mInflater = LayoutInflater.from(mContext);
    view = mInflater.inflate(R.layout.item_wallpaper, parent, false);
    return new WallpaperViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
    Picasso.get().load("https://free-wall-paper.herokuapp.com/"+mData.get(position).getImageUrl()).into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public static class WallpaperViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    View cardView;
    public WallpaperViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.image_view_wallpaper);
      cardView = itemView.findViewById(R.id.card_view_wallpaper);
    }
  }
}
