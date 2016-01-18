package com.example.emcako.birthdayreminder;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emcako.birthdayreminder.database.DatabaseHelper;
import com.example.emcako.birthdayreminder.database.Friend;
import com.example.emcako.birthdayreminder.fragments.FriendsFragment;

import java.util.List;

public class FriendsListAdapter extends ArrayAdapter<Friend>
{
    private final Activity activity;
    private List<Friend> friends;

    public FriendsListAdapter(Activity activity, int resource, List<Friend> friends) {
        super(activity, resource, friends);

        this.activity = activity;
        this.friends =  friends;
    }



    public View getView(final int position,View view,ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null, true);

        final int currentPosition = position;

        ImageButton btn= (ImageButton) rowView.findViewById(R.id.btn_delete);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Friend friendToDelete = (Friend) friends.get(currentPosition);
                String name = friendToDelete.getName();

                DatabaseHelper db = new DatabaseHelper(getContext());
                db.deleteFriend(friendToDelete);
                friends.remove(position);

                FriendsFragment.adapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "You just deleted " + name + " !", Toast.LENGTH_SHORT).show();

            }
        });

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.photo_icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(friends.get(position).getName());

        extratxt.setText(friends.get(position).getBirthday());

        String imgUriString = friends.get(position).getImagePath();

        if (imgUriString == null || imgUriString == "")
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
