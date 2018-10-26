package com.stetter.dhartmuseum.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stetter.dhartmuseum.model.Exhibition;

import java.util.List;

public interface ExhibitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exhibition exhibition);

    @Update
    void update (Exhibition exhibition);

    @Delete
    void delete (Exhibition exhibition);

    @Query("Select * from exhibition limit 30")
    LiveData<List<Exhibition>> getAll();

    @Query( "Select * from exhibition where id= :id" )
    Exhibition getById (long id);
}
