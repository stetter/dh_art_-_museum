package com.stetter.dhartmuseum.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stetter.dhartmuseum.model.Exhibtion;

@Dao
public interface ExhibtionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exhibtion exhibtion);

    @Update
    void update (Exhibtion exhibtion);

    @Delete
    void delete(Exhibtion exhibtion);

    @Query( " select * from exhibtion where id = :id")
    Exhibtion getById(long id);
}
