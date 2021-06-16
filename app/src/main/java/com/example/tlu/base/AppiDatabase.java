package com.example.tlu.base;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.RoomDatabase;

@Database(entities = {Building.class, Floor.class, NavConnections.class, NavPoints.class, PointType.class, Room.class, RoomName.class, RoomType.class}, version = 1, exportSchema = false)
public abstract class AppiDatabase extends RoomDatabase {
    public abstract DatabaseDao DatabaseDao();
    public abstract BuildingDao BuildingDao();
    public abstract FloorDao FloorDao();
    public abstract NavConnectionsDao NavConnectionsDao();
    public abstract PointTypeDao PointTypeDao();
    public abstract RoomDao RoomDao();
    public abstract RoomNameDao RoomNameDao();
    public abstract RoomTypeDao RoomTypeDao();

    /*public abstract String getDatabasePath();*/
}