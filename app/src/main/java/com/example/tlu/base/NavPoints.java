package com.example.tlu.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "nav_points"/*, foreignKeys = {@ForeignKey(entity = Room.class, parentColumns = "id", childColumns = "room_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = PointType.class, parentColumns = "id", childColumns = "point_type_id", onDelete = ForeignKey.CASCADE)}*/)
public class NavPoints {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id"/*, index = true*/)
    String id;

    @ColumnInfo(name = "xnav")
    double xNav;

    @ColumnInfo(name = "ynav")
    double yNav;

    @ColumnInfo(name = "point_type_id"/*, index = true*/)
    public int pointTypeID;

    @ColumnInfo(name = "room_id"/*, index = true*/)
    public int roomID;

}