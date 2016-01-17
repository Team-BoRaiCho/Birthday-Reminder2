package com.example.emcako.birthdayreminder;

import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;
import com.example.emcako.birthdayreminder.MainActivity.MainPageAdapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new AddActivity.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showPicturePicker(View view){
        // in onCreate or any event where your want the user to
        // select a file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                //selectedImagePath = getPath(selectedImageUri);

                ImageView iv = (ImageView) findViewById(R.id.iv_add_photo);
                iv.setImageURI(selectedImageUri);
//                Bitmap bmImg = BitmapFactory.decodeFile(selectedImagePath);
//                iv.setImageBitmap(bmImg);
            }
        }
    }

    public void AddFriendToDb(View view) {
        EditText firstNameEt = (EditText) findViewById(R.id.et_firstname);
        String fn = firstNameEt.getText().toString();

        EditText lastNameEt = (EditText) findViewById(R.id.et_lastname);
        String ln = lastNameEt.getText().toString();

        String name = fn + " " + ln;

        DatabaseHelper db = new DatabaseHelper(view.getContext());
        Friend friendToAdd = new Friend(name);
        db.addFriend(friendToAdd);

        Toast.makeText(this, "Friend added!", Toast.LENGTH_SHORT).show();
        goToFriendsList(view);
    }

    public void goToFriendsList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.mainPageAdapter.getItem(2);
        startActivity(intent);
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
            EditText textView = (EditText) getActivity().findViewById(R.id.BirthText);
            if (month < 11) {
                textView.setText(day + "." + "0" + month + "." + year);
            } else {
                textView.setText(day + "." + month + "." + year);
            }
        }
    }
}