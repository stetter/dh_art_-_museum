package com.stetter.dhartmuseum.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.stetter.dhartmuseum.data.database.ObjectDao;
import com.stetter.dhartmuseum.model.Record;

@Database(entities = {Record.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ObjectDatabase extends RoomDatabase {

    public abstract ObjectDao movieDAO();

    private static volatile ObjectDatabase INSTANCE;

    public static ObjectDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ObjectDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ObjectDatabase.class, "museum_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
