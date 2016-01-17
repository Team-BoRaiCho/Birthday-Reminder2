package com.example.emcako.birthdayreminder;

import android.graphics.drawable.Drawable;
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
        String firstName = FriendsFragment.itemname[b.getInt("position")];
        Integer photoId =  FriendsFragment.imgid[b.getInt("position")];

        TextView tv =(TextView) v.findViewById(R.id.tv_firstname_value);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_photo);

        tv.setText(firstName);
        iv.setImageResource(photoId);

        //tv.setText("This is an instance of MyDialogFragment");
        return v;
    }



}
