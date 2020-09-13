package com.example.freewall.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperViewPagerAdapter extends PagerAdapter {
  private Context mContext;
  private ArrayList<String> imageUrls;

  public WallpaperViewPagerAdapter(Context context, ArrayList<String> imageUrls) {
    this.mContext = context;
    this.imageUrls = imageUrls;
  }

  @Override
  public int getCount() {
    return imageUrls.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }


  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    ImageView imageView = new ImageView(mContext);
    Picasso.get()
      .load("https://free-wall-paper.herokuapp.com/" + imageUrls.get(position))
      .fit()
      .into(imageView);
    container.addView(imageView);
    return imageView;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
  }
}
