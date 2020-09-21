package com.example.freewall;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.freewall.adapters.WallpaperViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

public class ShowWallpaperActivity extends AppCompatActivity implements View.OnClickListener {
  private static final String TAG = "ShowWallpaperActivity";

  private ViewPager viewPager;

  private TextView btnLike;
  private TextView btnSetWallpaper;
  private FloatingActionButton btnDownload;
  private ArrayList<String> imagesName;
  private ArrayList<String> imagesUrl;

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
    imagesUrl = getIntent().getStringArrayListExtra("urls");

    viewPager = findViewById(R.id.view_pager);
    if (getIntent().hasExtra("urls")) {
      WallpaperViewPagerAdapter wallpaperViewPagerAdapter =
        new WallpaperViewPagerAdapter(this,
          imagesUrl, imagesName
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
    String nameOfWallpaper = imagesName.get(viewPager.getCurrentItem());
    String urlOfWallpaper = imagesUrl.get(viewPager.getCurrentItem());
    switch (v.getId()) {
      case R.id.btn_like:
        //code for like a wallpaper
        Toast.makeText(this, "Wallpaper Liked " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
      case R.id.btn_set_wallpaper:
        //code to set wallpaper
        setWallpaper(urlOfWallpaper);
        Toast.makeText(this, "Wallpaper Set as Home Screen " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
      case R.id.fab_download:
        //code to download wallpaper
        downloadImage(urlOfWallpaper, nameOfWallpaper);
        Toast.makeText(this, "Wallpaper Downloaded " + nameOfWallpaper, Toast.LENGTH_SHORT).show();
        break;
    }
  }

  private void setWallpaper(String url) {
    Picasso.get().load("https://free-wall-paper.herokuapp.com/" + url).into(new Target() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
          wallpaperManager.setBitmap(bitmap);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        Log.d("TAG", "onBitmapLoaded: ");
//        Toast.makeText(ShowWallpaperActivity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        Log.d("TAG", "FAILED");
        e.printStackTrace();
        Toast.makeText(ShowWallpaperActivity.this, "Loading image failed", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPrepareLoad(Drawable placeHolderDrawable) {
        Log.d("TAG", "Prepare Load");
        Toast.makeText(ShowWallpaperActivity.this, "Downloading image", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void downloadImage(String url, String name) {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

      // this will request for permission when user has not granted permission for the app
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    } else {
      url = "https://free-wall-paper.herokuapp.com/" + url;
      DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
      request.setDescription("Wallpaper Gallery");
      request.setTitle(name + " Wallpaper");
// in order for this if to run, you must use the android 3.2 to compile your app
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
      }
      request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + ".jpeg");

// get download service and enqueue file
      DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
      manager.enqueue(request);
    }
  }
}