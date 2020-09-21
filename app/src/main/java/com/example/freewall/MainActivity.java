package com.example.freewall;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.freewall.adapters.SectionPageAdapter;
import com.example.freewall.tabs.CategoryFragment;
import com.example.freewall.tabs.HomeFragment;
import com.example.freewall.tabs.TrendingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

  private SectionPageAdapter mSectionPageAdapter;

  private static final String TAG = ".MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
    ViewPager viewPager = findViewById(R.id.container);
    setupViewPager(viewPager);

    TabLayout tabLayout = findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);

    ////////////////////////////////

    Toolbar mToolbar = findViewById(R.id.walls_toolbar);
    mToolbar.setTitle("FreeWall");
    mToolbar.setTitleTextColor(getResources().getColor(R.color.colorText));
    setSupportActionBar(mToolbar);
  }

  public void setupViewPager(ViewPager viewPager) {
    mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
    mSectionPageAdapter.addFragment(HomeFragment.getInstance(), "Home");
    mSectionPageAdapter.addFragment(TrendingFragment.getInstance(), "Trending");
    mSectionPageAdapter.addFragment(CategoryFragment.getInstance(), "Premium");
    viewPager.setAdapter(mSectionPageAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.upload:
        Intent intent = new Intent(this, UploadWallpaper.class);
        startActivity(intent);
        return true;
      case R.id.about:
        Toast.makeText(
          this,
          "Wallpaper Gallery Project App",
          Toast.LENGTH_SHORT
        ).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}