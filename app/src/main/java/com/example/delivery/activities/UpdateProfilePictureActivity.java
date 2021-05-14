package com.example.delivery.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.delivery.R;
import com.example.delivery.helpers.UserHelper;
import com.example.delivery.utils.UserUtil;

public class UpdateProfilePictureActivity extends BaseActivity {

    private ImageView imageView;
    private Intent mData;

    private static final int IMAGE_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_picture);

        initView();
    }

    private void initView() {
        initNavBar(true, "Update Profile Picture", false);

        imageView = fd(R.id.uploaded_picture);
    }

    public void onUploadPictureClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                chooseImageFromGallery();
            }
        } else {
            chooseImageFromGallery();
        }
    }

    private void chooseImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseImageFromGallery();
            } else {
                Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            mData = data;
            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    public void onUpdateConfirmClick(View view) {
        String imageURL = mData.getData().toString();

        if (!UserUtil.updateProfilePicture(this, imageURL)) {
            return;
        }

        finish();

//        String email = UserHelper.getInstance().getEmail();
//        if (UserUtil.isManager(email)) {
//            Intent intent = new Intent(this, ManagerActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }
}