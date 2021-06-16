package com.example.tlu.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_name")
public class RoomName {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "ee")
    public String ee;

    @NonNull
    @ColumnInfo(name = "en")
    public String en;

    /*public RoomName(int id, String ee, String en){
        this.id = id;
        this.ee = ee;
        this.en = en;
    }*/
}