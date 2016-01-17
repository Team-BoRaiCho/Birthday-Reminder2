package com.example.emcako.birthdayreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.emcako.birthdayreminder.CustomListAdapter;
import com.example.emcako.birthdayreminder.MyDialogFragment;
import com.example.emcako.birthdayreminder.R;

public class FriendsFragment extends Fragment {

    ListView list;
    String[] itemname = {
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    Integer[] imgid = {
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.j,
            R.drawable.k,
    };


    String Slecteditem;
    View rootView;
    View tv;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_friednds, container, false);

        CustomListAdapter adapter = new CustomListAdapter(this.getActivity(), itemname, imgid);
        list = (ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               // Slecteditem = itemname[+position];
                DialogFragment newFragment = MyDialogFragment.newInstance();
                newFragment.show(getFragmentManager(), "dialog");
                return false;
            }
        });
        return rootView;
    }


}
