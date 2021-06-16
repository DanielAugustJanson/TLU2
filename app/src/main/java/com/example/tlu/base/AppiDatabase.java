package com.example.tlu.base;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.tlu.base.Building.class, com.example.tlu.base.Floor.class, com.example.tlu.base.NavConnections.class, com.example.tlu.base.NavPoints.class, com.example.tlu.base.PointType.class, com.example.tlu.base.Room.class, com.example.tlu.base.RoomName.class, com.example.tlu.base.RoomType.class}, version = 1)
public abstract class AppiDatabase extends RoomDatabase {
    /*public abstract DatabaseDao DatabaseDao();*/
    public abstract BuildingDao BuildingDao();
    public abstract com.example.tlu.base.FloorDao FloorDao();
    public abstract com.example.tlu.base.NavConnectionsDao NavConnectionsDao();
    public abstract com.example.tlu.base.PointTypeDao PointTypeDao();
    public abstract com.example.tlu.base.RoomDao RoomDao();
    public abstract com.example.tlu.base.RoomNameDao RoomNameDao();
    public abstract com.example.tlu.base.RoomTypeDao RoomTypeDao();

    /*public abstract String getDatabasePath();*/
}