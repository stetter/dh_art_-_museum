package com.stetter.dhartmuseum.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stetter.dhartmuseum.model.Exhibition;

@Dao
public interface ExhibtionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exhibition exhibtion);

    @Update
    void update (Exhibition exhibtion);

    @Delete
    void delete(Exhibition exhibtion);

    @Query( " select * from exhibition where id = :id")
    Exhibition getById(long id);
}
