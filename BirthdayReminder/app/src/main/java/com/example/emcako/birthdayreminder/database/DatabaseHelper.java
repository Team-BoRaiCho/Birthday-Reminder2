package com.example.emcako.birthdayreminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emcako.birthdayreminder.database.Friend;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "database.db";

    private static final String TABLE_FRIENDS = "friends";

    // Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_GIFTS = "gifts";
    private static final String KEY_IMAGE_PATH = "imagePath";

    private static final String CREATE_FRIENDS_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
            + KEY_BIRTHDAY + " TEXT," + KEY_GIFTS + " TEXT," + KEY_IMAGE_PATH + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FRIENDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);

        // Create tables again
        onCreate(db);
    }

    public void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
    }

    // Adding new contact
    public void addFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName()); // Contact Name
        values.put(KEY_BIRTHDAY, friend.getBirthday()); // Contact Birthday
        values.put(KEY_GIFTS, friend.getGifts());
        values.put(KEY_IMAGE_PATH, friend.getImagePath());

        // Inserting Row
        db.insert(TABLE_FRIENDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Friend getFriend(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIENDS, new String[]{KEY_ID,
                        KEY_NAME, KEY_BIRTHDAY, KEY_GIFTS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        int currentId = Integer.parseInt(cursor.getString(0));
        String currentFriendName = cursor.getString(1);
        String currentBirthday = cursor.getString(2);
        String currentGifts = cursor.getString(3);
        String currentImagePath = cursor.getString(4);

        Friend friend = new Friend(currentId, currentFriendName, currentBirthday, currentGifts, currentImagePath);

        // return contact
        return friend;
    }

    // Getting All Contacts
    public List<Friend> getAllContacts() {
        List<Friend> friendsList = new ArrayList<Friend>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setID(Integer.parseInt(cursor.getString(0)));
                friend.setName(cursor.getString(1));
                friend.setBirthday(cursor.getString(2));
                friend.setGifts(cursor.getString(3));
                friend.setImagePath(cursor.getString(4));

                // Adding contact to list
                friendsList.add(friend);

            } while (cursor.moveToNext());
        }

        // return contact list
        return friendsList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FRIENDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_BIRTHDAY, friend.getBirthday());
        values.put(KEY_GIFTS, friend.getGifts());
        values.put(KEY_IMAGE_PATH, friend.getImagePath());

        // updating row
        return db.update(TABLE_FRIENDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(friend.getID()) });
    }

    // Deleting single contact
    public void deleteFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDS, KEY_ID + " = ?",
                new String[] { String.valueOf(friend.getID()) });

        db.close();
    }
}
