package com.example.tlu.base;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//@Fts4
@Entity(tableName = "nav_connections"/*, foreignKeys = {@ForeignKey(entity = NavPoints.class, parentColumns = "rowid", childColumns = "nav_points_id1", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = NavPoints.class, parentColumns = "rowid", childColumns = "nav_points_id2", onDelete = ForeignKey.CASCADE)},
        indices = {@Index("nav_points_id1"), @Index("nav_points_id2")}*/)
public class NavConnections {
    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @NonNull
    @ColumnInfo(name = "nav_points_id1")
    String navPointsID1;

    @NonNull
    @ColumnInfo(name = "nav_points_id2")
    String navPointsID2;

    @ColumnInfo(name = "needs_key")
    boolean needsKey;

    @ColumnInfo(name = "distance")
    double distance;

}