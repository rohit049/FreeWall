package com.example.freewall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.freewall.adapters.WallpaperViewPagerAdapter;
import com.example.freewall.models.WallpaperItem;
import com.squareup.picasso.Picasso;

public class ShowWallpaperActivity extends AppCompatActivity {
  private static final String TAG = "ShowWallpaperActivity";
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_wallpaper);

    ViewPager viewPager = findViewById(R.id.view_pager);
    if (getIntent().hasExtra("urls")) {
      WallpaperViewPagerAdapter wallpaperViewPagerAdapter =
        new WallpaperViewPagerAdapter(this,
          getIntent().getStringArrayListExtra("urls")
        );
      viewPager.setAdapter(wallpaperViewPagerAdapter);
      viewPager.setCurrentItem(getIntent().getIntExtra("selected_wallpaper", 0));
    }


  }
}