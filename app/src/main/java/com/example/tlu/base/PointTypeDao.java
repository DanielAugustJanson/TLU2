package com.example.tlu.base;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.tlu.base.PointType;

@Dao
public interface PointTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PointType... pointTypes);

    @Delete
    void delete(PointType pointType);
}