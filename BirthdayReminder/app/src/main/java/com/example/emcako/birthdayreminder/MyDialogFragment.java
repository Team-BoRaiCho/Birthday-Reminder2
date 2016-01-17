package com.example.emcako.birthdayreminder;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emcako.birthdayreminder.fragments.FriendsFragment;

public  class MyDialogFragment extends DialogFragment {
    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment, container, false);

        Bundle b = getArguments();
        String name = FriendsFragment.friends.get(b.getInt("position")).getName();
        String birthday = FriendsFragment.friends.get(b.getInt("position")).getBirthday();
        String gifts = FriendsFragment.friends.get(b.getInt("position")).getGifts();
        String imgUriString = FriendsFragment.friends.get(b.getInt("position")).getImagePath();


        //String firstName = FriendsFragment.itemname[b.getInt("position")];
        //Integer photoId =  FriendsFragment.imgid[b.getInt("position")];

        TextView nameTv =(TextView) v.findViewById(R.id.tv_firstname_value);
        nameTv.setText(name);

        TextView birthdayTv =(TextView) v.findViewById(R.id.tv_birthday);
        birthdayTv.setText(birthday);

        TextView giftsTv = (TextView) v.findViewById(R.id.tv_giftideas);
        giftsTv.setText(gifts);

        ImageView imageView = (ImageView) v.findViewById(R.id.iv_photo);

        if (imgUriString == null || imgUriString=="")
        {
            imageView.setImageResource(R.drawable.android_300x300);
        }
        else
        {
            Uri imgUri = Uri.parse(imgUriString);
            imageView.setImageURI(imgUri);
        }

        return v;
    }
}
