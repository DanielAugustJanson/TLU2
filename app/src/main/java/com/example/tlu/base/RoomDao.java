package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Room... rooms);

    @Delete
    void delete(Room room);

    @Update
    int updateRoom(Room room);

    @Query("SELECT * FROM room WHERE floor_id = :floor")
    List<Room> getFloor(int floor);
}