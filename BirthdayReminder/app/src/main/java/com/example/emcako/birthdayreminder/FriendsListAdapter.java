package com.example.emcako.birthdayreminder;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;

import java.util.List;

/**
 * Created by eLa on 17-Jan-16.
 */
public class FriendsListAdapter extends ArrayAdapter<Friend>
{
    private final Activity activity;
    private final List<Friend> friends;

    public FriendsListAdapter(Activity activity, int resource, List<Friend> friends) {
        super(activity, resource, friends);

        this.activity = activity;
        this.friends =  friends;
    }


    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(friends.get(position).getName());

        extratxt.setText(friends.get(position).getBirthday());

        String imgUriString = friends.get(position).getImagePath();
        if (imgUriString == null || imgUriString=="")
        {
            imageView.setImageResource(R.drawable.android_300x300);
        }
        else
        {
            Uri imgUri = Uri.parse(imgUriString);
            imageView.setImageURI(imgUri);
        }

        return rowView;
    };

}
