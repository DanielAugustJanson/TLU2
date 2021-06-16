package com.example.tlu.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "building")
public class Building {
    @PrimaryKey
    @ColumnInfo(name = "id"/*, index = true*/)
    public int id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

}