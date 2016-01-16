package com.example.emcako.birthdayreminder;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public  class MyDialogFragment extends DialogFragment {
    static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment, container, false);
        View tv = v.findViewById(R.id.tv_dialigfragment);
        ((TextView)tv).setText("This is an instance of MyDialogFragmentfgjfghdfgfgdfgdfgdfgvvdfvdfvdfvdfvdfvd");
        return v;
    }
}
