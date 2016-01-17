package com.example.emcako.birthdayreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.emcako.birthdayreminder.fragments.FriendsFragment;
import com.example.emcako.birthdayreminder.fragments.LocationFragemnt;
import com.example.emcako.birthdayreminder.fragments.MyAccountFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_mainActivity);

        MainPageAdapter adapter2 = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter2);
    }

    public void GoToCamera(View view) {
        Intent takePhotoIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhotoIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.imageView2);
            imageview.setImageBitmap(image);
        }
    }

    public class MainPageAdapter extends FragmentPagerAdapter {

        public MainPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MyAccountFragment();
                case 1:
                    return new FriendsFragment();
                case 2:
                    return new LocationFragemnt();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void goToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void GoToAddActivity(View view) {
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);
    }
}