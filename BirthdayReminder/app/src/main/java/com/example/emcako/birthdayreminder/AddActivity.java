package com.example.emcako.birthdayreminder;

import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private static Uri selectedImageUri = Uri.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new AddActivity.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showPicturePicker(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();

                ImageView iv = (ImageView) findViewById(R.id.iv_add_photo);
                iv.setImageURI(selectedImageUri);
            }
        }
    }

    MediaPlayer player;
    public void AddFriendToDb(View view) {
        EditText firstNameEt = (EditText) findViewById(R.id.et_firstname);
        String fn = firstNameEt.getText().toString();
        EditText lastNameEt = (EditText) findViewById(R.id.et_lastname);
        String ln = lastNameEt.getText().toString();

        if (isValidName(fn) || isValidName(ln)) {
            player=MediaPlayer.create(AddActivity.this,R.raw.invalid_input);
            player.start();
            Toast.makeText(this, "Names cannot be less than 3 symbols!", Toast.LENGTH_SHORT).show();

        } else {
            String name = fn + " " + ln;

            EditText birthdayEt = (EditText) findViewById(R.id.et_birthday);
            String bd = birthdayEt.getText().toString();

            EditText giftIdeasEt = (EditText) findViewById(R.id.et_giftideas);
            String giftIdeas = giftIdeasEt.getText().toString();

            String imageUri;
            if (selectedImageUri == Uri.EMPTY)
            {
                imageUri = null;
            }
            else {
                imageUri = selectedImageUri.toString();
            }

            Friend friendToAdd = new Friend(name);
            friendToAdd.setBirthday(bd);
            friendToAdd.setImagePath(imageUri);
            friendToAdd.setGifts(giftIdeas);

            DatabaseHelper db = new DatabaseHelper(view.getContext());
            db.addFriend(friendToAdd);
            //db.close();

            //FriendsFragment.adapter.notifyDataSetChanged();

            Toast.makeText(this, "Friend added!", Toast.LENGTH_SHORT).show();
            goToFriendsList(view);
        }
    }

    public boolean isValidName(String name) {
        return (name.length() < 3);
    }

    public void goToFriendsList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //MainActivity.mainPageAdapter.getItem(2);
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
            EditText textView = (EditText) getActivity().findViewById(R.id.et_birthday);
            if (month < 11) {
                textView.setText(day + "." + "0" + month + "." + year);
            } else {
                textView.setText(day + "." + month + "." + year);
            }
        }
    }
}