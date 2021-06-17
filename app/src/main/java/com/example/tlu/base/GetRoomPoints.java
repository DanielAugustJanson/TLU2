package com.example.tlu.base;

public class GetRoomPoints implements Runnable{
    @Override
    public void run() {
        com.example.tlu.MainActivity.forPoints = com.example.tlu.MainActivity.db.RoomDao().getFloor(com.example.tlu.MainActivity.floorDisplayed);
    }
}
