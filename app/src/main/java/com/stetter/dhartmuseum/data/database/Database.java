package com.stetter.dhartmuseum.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.stetter.dhartmuseum.interfaces.ExhibitionDao;
import com.stetter.dhartmuseum.model.Exhibition;

@android.arch.persistence.room.Database(entities = {Exhibition.class},version =1 ,exportSchema = false)
@TypeConverters( Converters.class )
public abstract class Database extends RoomDatabase {


    public abstract ExhibitionDao exhibitionDao();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                            Database.class, "dhartmuseum" )
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
