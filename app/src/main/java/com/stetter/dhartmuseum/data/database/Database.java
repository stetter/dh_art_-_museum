package com.stetter.dhartmuseum.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.stetter.dhartmuseum.data.local.GalleryRecordDAO;
import com.stetter.dhartmuseum.data.local.ObjectDao;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.model.Record;

@android.arch.persistence.room.Database(entities = {Record.class, GalleryRecord.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class Database extends RoomDatabase {

    public abstract ObjectDao movieDAO();
    public abstract GalleryRecordDAO getGalleryRecordDAO();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "museum_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}