package com.stetter.dhartmuseum.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.stetter.dhartmuseum.interfaces.ExhibitionDao;
@android.arch.persistence.room.Database(entities = {ExhibitionDao.class},version =1 )
public abstract class Database extends RoomDatabase {


    public abstract ExhibitionDao getExhibitionDao();

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
