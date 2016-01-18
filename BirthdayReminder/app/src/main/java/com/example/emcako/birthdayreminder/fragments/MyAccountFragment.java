package com.example.emcako.birthdayreminder.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emcako.birthdayreminder.R;
import com.example.emcako.birthdayreminder.database.DatabaseHelper;

import org.w3c.dom.Text;

public class MyAccountFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_myaccount, container, false);

        DatabaseHelper db = new DatabaseHelper(this.getContext());
        int contacts = db.getContactsCount();
        String contactsNumber = String.valueOf(contacts);

        TextView tv = (TextView) rootView.findViewById(R.id.tv_friends_count_value);
        tv.setText(contactsNumber);

        return rootView;
    }
}

