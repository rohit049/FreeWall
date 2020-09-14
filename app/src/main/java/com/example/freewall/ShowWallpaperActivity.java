package com.example.freewall;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.freewall.adapters.WallpaperViewPagerAdapter;

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


//    imageView = findViewById(R.id.image_view_show_wallpaper);
//    if (getIntent().hasExtra("selected_wallpaper")) {
//      WallpaperItem wallpaperItem = getIntent().getParcelableExtra("selected_wallpaper");
//      assert wallpaperItem != null;
//      Picasso
//        .get()
//        .load("https://free-wall-paper.herokuapp.com/" + wallpaperItem.getImageUrl())
//        .into(imageView);
//
//      Log.d(TAG, "onCreate: " + wallpaperItem.getName());
//    }

  }
}