package com.stetter.dhartmuseum.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stetter.dhartmuseum.model.Record;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Record record);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Record> record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record record);

    @Query("Select * from records limit 30")
    Flowable<List<Record>> getAll(); // Flowable é um observavel que podemos usar com RoomDatabase
}
