package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseDao {
    @Query("SELECT * FROM nav_points WHERE room_id IS :roomID")
    List<NavPoints> findDestination(int roomID);



}