package com.example.emcako.birthdayreminder.fragments.dummy;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.emcako.birthdayreminder.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    public static void GenerateItems(Context context){
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(context, i));
        }
    }


    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    private static DummyItem createDummyItem(Context context, int position) {
        Drawable avatarDefault = (Drawable) context.getResources().getDrawable(R.drawable.icon_person);
        return new DummyItem(String.valueOf(position), "Item " + position,
                makeDetails(position),
                avatarDefault);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {

        public final String id;
        public final String content;
        public final String details;
        public final Drawable avatar;

        public DummyItem(String id, String content, String details, Drawable avatar) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
