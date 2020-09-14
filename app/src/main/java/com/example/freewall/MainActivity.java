package com.example.freewall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.example.freewall.adapters.SectionPageAdapter;
import com.example.freewall.tabs.CategoryFragment;
import com.example.freewall.tabs.HomeFragment;
import com.example.freewall.tabs.TrendingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

  private SectionPageAdapter mSectionPageAdapter;

  //////////////////////////////////////////

  RequestQueue mRequestQueue;
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

  }

  public void setupViewPager(ViewPager viewPager) {

    mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
    mSectionPageAdapter.addFragment(HomeFragment.getInstance(), "Home");
    mSectionPageAdapter.addFragment(TrendingFragment.getInstance(), "Trending");
    mSectionPageAdapter.addFragment(CategoryFragment.getInstance(), "Category");
    viewPager.setAdapter(mSectionPageAdapter);
  }




}