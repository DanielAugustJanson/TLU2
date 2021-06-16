package com.example.tlu.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BuildingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Building... buildings);

    @Delete
    void delete(Building building);

    @Query("SELECT * FROM building")
    List<Building> allBuildings();

}