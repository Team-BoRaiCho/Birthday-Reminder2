package com.example.emcako.birthdayreminder.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.emcako.birthdayreminder.FriendsListAdapter;
import com.example.emcako.birthdayreminder.MyDialogFragment;
import com.example.emcako.birthdayreminder.R;
import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;

import java.util.List;

public class FriendsFragment extends Fragment {

    ListView list;
    public static String[] itemname = {
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    public static Integer[] imgid = {
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.j,
            R.drawable.k,
    };
    MediaPlayer player;

    View rootView;
    public static List<Friend> friends;
    public static FriendsListAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_friends, container, false);


        DatabaseHelper db = new DatabaseHelper(getContext());
        int count = db.getContactsCount();
        friends = db.getAllContacts();

        TextView b = (TextView) rootView.findViewById(R.id.tv_friends);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        if (count > 0) {
            //CustomListAdapter adapter = new CustomListAdapter(this.getActivity(), itemname, imgid);

            adapter = new FriendsListAdapter(this.getActivity(), 0, friends);
            list = (ListView) rootView.findViewById(R.id.list);
            list.setAdapter(adapter);


            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    DialogFragment newFragment = MyDialogFragment.newInstance();

                    player = MediaPlayer.create(getActivity(), R.raw.desk_bell);
                    player.start();

                    Bundle b = new Bundle();
                    b.putInt("position", position);
                    newFragment.setArguments(b);

                    newFragment.show(getFragmentManager(), "dialog");
                    return false;
                }
            });

        } else {
            TextView tv = (TextView) rootView.findViewById(R.id.tv_friends);
            tv.setText("You still haven't added friends.. :(");
        }

        return rootView;
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        builder.setContentTitle("Birtdhays");
        builder.setContentText("No birthdays today!");
        builder.setSmallIcon(R.drawable.ic_stat_notification);

        Notification notificatonObject = builder.build();

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, notificatonObject);
    }


}
