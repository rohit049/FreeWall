package com.example.freewall.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WallpaperItem implements Parcelable {
  private String name;
  private String imageUrl;
  private int likes;

  public WallpaperItem(String name, String imageUrl, int likes) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.likes = likes;
  }

  protected WallpaperItem(Parcel in) {
    name = in.readString();
    imageUrl = in.readString();
    likes = in.readInt();
  }

  public static final Creator<WallpaperItem> CREATOR = new Creator<WallpaperItem>() {
    @Override
    public WallpaperItem createFromParcel(Parcel in) {
      return new WallpaperItem(in);
    }

    @Override
    public WallpaperItem[] newArray(int size) {
      return new WallpaperItem[size];
    }
  };

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(imageUrl);
    dest.writeInt(likes);
  }
}
