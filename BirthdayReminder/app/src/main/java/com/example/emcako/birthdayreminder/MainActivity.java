package com.example.emcako.birthdayreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            month += 1;
            TextView textView = (TextView) getActivity().findViewById(R.id.BirthText);
            if (month < 11) {
                textView.setText(day + "." + "0" + month + "." + year);
            } else {
                textView.setText(day + "." + month + "." + year);
            }
        }
    }

    public void goToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void GoToAddActivity(View view) {
        Intent intent = new Intent(this,ADD.class);
        startActivity(intent);
    }
}
