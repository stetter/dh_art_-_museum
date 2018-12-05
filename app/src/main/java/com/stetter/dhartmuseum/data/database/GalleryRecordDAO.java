package com.stetter.dhartmuseum.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stetter.dhartmuseum.home.model.GalleryRecord;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface GalleryRecordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GalleryRecord galleryRecord);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GalleryRecord> galleryRecord);

    @Update
    void update(GalleryRecord galleryRecord);

    @Delete
    void delete(GalleryRecord galleryRecord);

    @Query("Select * from GalleryRecords limit 30")
    Flowable<List<GalleryRecord>> getAll();

    @Query("Select * from GalleryRecords where id = :id")
    GalleryRecord getById(long id);
}
