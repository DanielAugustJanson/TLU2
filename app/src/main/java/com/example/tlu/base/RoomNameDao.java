package com.example.tlu.base;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RoomNameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RoomName... RoomNames);

    @Delete
    void delete(RoomName roomName);

    @Query("SELECT en FROM room_name WHERE rowid = :roomNameID LIMIT 1")
    String getEn(int roomNameID);

    @Query("SELECT ee FROM room_name WHERE rowid = :roomNameID LIMIT 1")
    String getEE(int roomNameID);
}