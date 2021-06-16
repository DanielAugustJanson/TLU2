package com.example.tlu.base;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface FloorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Floor... floors);

    @Delete
    void delete(Floor floor);
}