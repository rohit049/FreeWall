package com.example.freewall.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.freewall.R;
import com.example.freewall.ShowWallpaperActivity;
import com.example.freewall.adapters.WallpaperAdapter;
import com.example.freewall.api.FetchData;
import com.example.freewall.models.WallpaperItem;

import java.util.ArrayList;
import java.util.Objects;

public class TrendingFragment extends Fragment implements FetchData.TransactionComplete, WallpaperAdapter.OnWallListener {
  RequestQueue mRequestQueue;
  final String URL = "https://free-wall-paper.herokuapp.com/api/v1/walls?sort=-likes";
  private static final String TAG = ".MainActivity";
  private ArrayList<WallpaperItem> mWallpapers;
  private RecyclerView mRecyclerView;
  private ProgressBar mProgressBar;
  private WallpaperAdapter mWallpaperAdapter;
  public static TrendingFragment uniqInstance;

  public static TrendingFragment getInstance() {
    if (uniqInstance == null)
      uniqInstance = new TrendingFragment();
    return uniqInstance;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_trending, container, false);
    mRecyclerView = view.findViewById(R.id.recyclerview);
    mProgressBar = view.findViewById(R.id.progress_bar);
    mRecyclerView.setVisibility(View.GONE);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
    mWallpapers = new ArrayList<>();
    if (mRecyclerView == null) {
      Log.d(TAG, "TrendingFragment: onCreate: Recycler view not created");
    } else {
      initRecyclerView();
      FetchData fetchData = new FetchData();
      fetchData.onFetchData(URL, getContext(), getInstance());
    }
  }

  private void initRecyclerView() {
    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    mWallpaperAdapter = new WallpaperAdapter(mWallpapers, this);
    mRecyclerView.setAdapter(mWallpaperAdapter);
  }

  @Override
  public void onWallClick(int position) {
    Log.d(TAG, "onWallClick: " + position);
    Intent intent = new Intent(getActivity(), ShowWallpaperActivity.class);
    ArrayList<String> urls = new ArrayList<>();

    for (int i = 0; i < mWallpaperAdapter.getItemCount(); i++) {
      urls.add(mWallpapers.get(i).getImageUrl());
    }
    intent.putExtra("urls", urls);
    intent.putExtra("selected_wallpaper", position);
    startActivity(intent);
  }

  @Override
  public void onNotifyAdapter() {
    Log.d(TAG, "TrendingFragment: onNotifyAdapter: Updating adapter.....");
    mRecyclerView.setVisibility(View.VISIBLE);
    mWallpaperAdapter.notifyDataSetChanged();
    Log.d(TAG, "TrendingFragment: onNotifyAdapter: Item Count " + mWallpaperAdapter.getItemCount());
    mProgressBar.setVisibility(View.GONE);
  }

  @Override
  public void addItemToArrayList(WallpaperItem item) {
    this.mWallpapers.add(item);
  }
}
