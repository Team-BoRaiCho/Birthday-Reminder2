package com.example.emcako.birthdayreminder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;
import com.example.emcako.birthdayreminder.fragments.FriendsFragment;
import com.example.emcako.birthdayreminder.fragments.LocationFragemnt;
import com.example.emcako.birthdayreminder.fragments.MyAccountFragment;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 200;
    public static MainPageAdapter mainPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deleteDatabase(DatabaseHelper.DATABASE_NAME);
        //GenerateSomeFriends();

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_mainActivity);

        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);
    }

    public void GoToCamera(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void GenerateSomeFriends() {
        DatabaseHelper db = new DatabaseHelper(this);
        Friend friend = new Friend("Ravi");
        friend.setBirthday("20.05.1986");
        db.addFriend(friend);
        db.addFriend(new Friend("Srinivas"));
    }
}