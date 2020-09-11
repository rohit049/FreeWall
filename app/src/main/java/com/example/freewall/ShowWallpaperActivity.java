package com.example.freewall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.freewall.models.WallpaperItem;
import com.squareup.picasso.Picasso;

public class ShowWallpaperActivity extends AppCompatActivity {
  private static final String TAG = "ShowWallpaperActivity";
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_wallpaper);

    imageView = findViewById(R.id.image_view_show_wallpaper);
    if (getIntent().hasExtra("selected_wallpaper")) {
      WallpaperItem wallpaperItem = getIntent().getParcelableExtra("selected_wallpaper");
      assert wallpaperItem != null;
      Picasso
        .get()
        .load("https://free-wall-paper.herokuapp.com/" + wallpaperItem.getImageUrl())
        .into(imageView);

      Log.d(TAG, "onCreate: " + wallpaperItem.getName());
    }

  }
}