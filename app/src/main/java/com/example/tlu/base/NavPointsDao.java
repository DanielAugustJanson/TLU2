package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NavPointsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(NavPoints... navPoints);

    @Delete
    void delete(NavPoints navPoint);
}