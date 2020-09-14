package com.example.freewall.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freewall.R;
import com.example.freewall.models.WallpaperItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {
  private ArrayList<WallpaperItem> mWallpapers;
  private OnWallListener mOnWallListener;

  public WallpaperAdapter(ArrayList<WallpaperItem> data, OnWallListener onWallListener) {
    this.mWallpapers = data;
    mOnWallListener = onWallListener;
  }

  @NonNull
  @Override
  public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
    return new WallpaperViewHolder(view, mOnWallListener);
  }

  @Override
  public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
    holder.textViewLikes.setText("Likes: " + mWallpapers.get(position).getLikes());
    holder.textViewName.setText(mWallpapers.get(position).getName());
    Picasso
      .get()
      .load("https://free-wall-paper.herokuapp.com/" + mWallpapers.get(position).getImageUrl())
      .into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return mWallpapers.size();
  }

  public static class WallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    TextView textViewName;
    TextView textViewLikes;
    OnWallListener onWallListener;

    public WallpaperViewHolder(@NonNull View itemView, OnWallListener onWallListener) {
      super(itemView);
      imageView = itemView.findViewById(R.id.image_view_wallpaper);
      textViewName = itemView.findViewById(R.id.text_view_name);
      textViewLikes = itemView.findViewById(R.id.text_view_likes);

      imageView.setColorFilter(new PorterDuffColorFilter(Color.argb(45, 93, 64, 55), PorterDuff.Mode.DARKEN));
      this.onWallListener = onWallListener;

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      onWallListener.onWallClick(getAdapterPosition());
    }
  }

  public interface OnWallListener {
    void onWallClick(int position);
  }
}
