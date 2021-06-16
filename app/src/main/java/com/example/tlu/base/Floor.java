package com.example.tlu.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "floor")
public class Floor {
    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;
}