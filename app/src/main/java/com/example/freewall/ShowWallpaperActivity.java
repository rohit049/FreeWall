package com.example.freewall;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.freewall.adapters.WallpaperViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShowWallpaperActivity extends AppCompatActivity implements View.OnClickListener {
  private static final String TAG = "ShowWallpaperActivity";

  private ViewPager viewPager;

  private TextView btnLike;
  private TextView btnSetWallpaper;
  private FloatingActionButton btnDownload;
  private ArrayList<String> imagesName;
  private String nameOfWallpaper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_wallpaper);

    btnLike = findViewById(R.id.btn_like);
    btnDownload = findViewById(R.id.fab_download);
    btnSetWallpaper = findViewById(R.id.btn_set_wallpaper);

    btnLike.setOnClickListener(this);
    btnDownload.setOnClickListener(this);
    btnSetWallpaper.setOnClickListener(this);

    imagesName = getIntent().getStringArrayListExtra("names");
    viewPager = findViewById(R.id.view_pager);
    if (getIntent().hasExtra("urls")) {
      WallpaperViewPagerAdapter wallpaperViewPagerAdapter =
        new WallpaperViewPagerAdapter(this,
          getIntent().getStringArrayListExtra("urls"),
          imagesName
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

  @Override
  public void onClick(View v) {
    nameOfWallpaper = imagesName.get(viewPager.getCurrentItem());
    switch (v.getId()) {
      case R.id.btn_like:
        //code for like a wallpaper
        Toast.makeText(this, "Wallpaper Liked " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
      case R.id.btn_set_wallpaper:
        //code to set wallpaper
        Toast.makeText(this, "Wallpaper Set as Home Screen " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
      case R.id.fab_download:
        //code to download wallpaper
        Toast.makeText(this, "Wallpaper Downloaded " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
    }
  }
}