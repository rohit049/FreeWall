package com.example.freewall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class UploadWallpaper extends AppCompatActivity implements View.OnClickListener {
  private final int IMG_REQUEST = 1;
  private ImageView mImageView;
  private EditText mEditTextName;
  private EditText mEditTextType;
  private Button mButtonUpload;
  private Bitmap bitmap;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_upload);

    mImageView = findViewById(R.id.image_upload);
    mEditTextName = findViewById(R.id.edit_text_name);
    mEditTextType = findViewById(R.id.edit_text_brand);
    mButtonUpload = findViewById(R.id.btn_upload);

    mImageView.setClickable(true);
    mImageView.setOnClickListener(this);
    mButtonUpload.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.image_upload:
        selectImage();
        break;
      case R.id.btn_upload:
        Toast.makeText(this, "Will Upload this Image", Toast.LENGTH_SHORT).show();
        uploadWallpaper();
        break;
    }

  }

  private void uploadWallpaper() {

  }

  private void selectImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent, IMG_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
      Uri path = data.getData();
      try {
        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
        mImageView.setImageBitmap(bitmap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
