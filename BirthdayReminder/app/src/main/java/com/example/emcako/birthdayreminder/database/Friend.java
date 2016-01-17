package com.example.emcako.birthdayreminder.database;

public class Friend {
    int id;
    String name;
    String birthday;
    String gifts;
    //String[] gifts;
    String imagePath;

    public Friend() {

    }

    public Friend(String name) {
        this.name = name;
    }

    public Friend(int id, String name, String birthday, String gifts, String imagePath) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gifts = gifts;
        this.imagePath = imagePath;
    }

    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting name
    public String getName() {
        return this.name;
    }

    // setting name
    public void setName(String name) {
        this.name = name;
    }

    // getting birthday
    public String getBirthday() {
        return this.birthday;
    }

    // setting phone number
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGifts() {
        return this.gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
