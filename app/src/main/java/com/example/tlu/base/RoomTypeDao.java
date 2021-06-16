package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface RoomTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RoomType... roomTypes);

    @Delete
    void delete(RoomType roomType);
}