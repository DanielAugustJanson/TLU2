package com.example.tlu.base;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NavConnectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(NavConnections... navConnections);

    @Delete
    void delete(NavConnections navConnection);

    @Query("SELECT * FROM nav_connections WHERE needs_key = 0")
    List<NavConnections> connectionsSansKey();

    @Query("SELECT * FROM nav_connections")
    List<NavConnections> connectionsAll();
}