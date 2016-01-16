package com.example.emcako.birthdayreminder;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public  class MyDialogFragment extends DialogFragment {
    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment, container, false);
        View tv = v.findViewById(R.id.tv_dialigfragment);
        //((TextView)tv).setText("This is an instance of MyDialogFragmentfgjfghdfgfgdfgdfgdfgvvdfvdfvdfvdfvdfvd");
        return v;
    }
}
