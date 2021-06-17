package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseDao {
    @Query("SELECT * FROM nav_points WHERE room_id IS :roomID")
    List<NavPoints> findDestination(int roomID);

    @Query("SELECT * FROM room INNER JOIN room_name ON room.room_name_id = room_name.id WHERE code LIKE :search OR ee LIKE :search OR en LIKE :search")
    List<Room> lookForRoom(String search);

    @Query("SELECT * FROM nav_connections WHERE nav_points_id1 = :neighbourSeeker OR nav_points_id2 = :neighbourSeeker")
    List<NavConnections> lookForNeighbour(String neighbourSeeker);
    
}