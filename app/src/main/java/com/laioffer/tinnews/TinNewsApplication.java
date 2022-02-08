package com.laioffer.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.laioffer.tinnews.database.NewsDao;
import com.laioffer.tinnews.database.NewsDatabase;

public class TinNewsApplication extends Application {
    private static NewsDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, NewsDatabase.class, "tinnews_db").build();
    }
    public static NewsDatabase getDatabase() {
        return database;
    }
}
