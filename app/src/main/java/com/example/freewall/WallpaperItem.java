package com.example.freewall;

public class WallpaperItem {
  private String name;
  private String imageUrl;
  private int likes;

  public WallpaperItem(String name, String imageUrl, int likes) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.likes = likes;
  }

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
}
