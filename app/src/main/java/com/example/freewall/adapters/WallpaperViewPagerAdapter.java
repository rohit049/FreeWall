package com.example.freewall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.freewall.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class WallpaperViewPagerAdapter extends PagerAdapter {

  private static final String TAG = "WallpaperViewPagerAdapt";

  private Context mContext;
  private ArrayList<String> imageUrls;
  private ArrayList<String> imageNames;
  private LayoutInflater mLayoutInflater;


  public WallpaperViewPagerAdapter(Context context, ArrayList<String> imageUrls, ArrayList<String> imageNames) {
    this.mContext = context;
    this.imageUrls = imageUrls;
    this.imageNames = imageNames;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return imageUrls.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == ((RelativeLayout) object);
  }


  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {

    View itemView = mLayoutInflater.inflate(R.layout.wallpaper_item, container, false);

    ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
    TextView textView = (TextView) itemView.findViewById(R.id.textView_name);

    Picasso.get()
      .load("https://free-wall-paper.herokuapp.com/" + imageUrls.get(position))
      .fit()
      .into(imageView);


    assert textView != null;
    textView.setText(imageNames.get(position));

    container.addView(itemView);

    return itemView;

//    ImageView imageView = new ImageView(mContext);
//    Picasso.get()
//      .load("https://free-wall-paper.herokuapp.com/" + imageUrls.get(position))
//      .fit()
//      .into(imageView);
//    container.addView(imageView);
//    return imageView;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//    container.removeView((View) object);
    container.removeView((RelativeLayout) object);
  }
}
