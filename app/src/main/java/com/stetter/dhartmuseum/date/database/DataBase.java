package com.stetter.dhartmuseum.date.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.stetter.dhartmuseum.interfaces.ExhibtionDao;

//@Database(entities = {ExhibtionDao.class},version = 1)

public abstract class DataBase {//extends RoomDatabase {

   /* public abstract ExhibtionDao getExhibtionDap();

    private static volatile DataBase INSTANCE;

    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                            DataBase.class, "dhartmuseum" )
                            .build();
                }
            }
        }
        return INSTANCE;
    }*/
}
