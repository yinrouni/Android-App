package com.cs5520.numad20s_rouniyin;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LinkItem.class}, version = 1)
public abstract class LinkRoomDatabase extends RoomDatabase {
    public abstract LinkDao linkDao();

    private static LinkRoomDatabase INSTANCE;

    static LinkRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LinkRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    LinkRoomDatabase.class,
                                    "link_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
