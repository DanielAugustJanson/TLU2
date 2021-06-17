package com.example.tlu.base;

public class LookForRoomByCode implements Runnable{
    @Override
    public void run() {
        com.example.tlu.MainActivity.roomsForLookUp = com.example.tlu.MainActivity.db.RoomDao().getFloor(com.example.tlu.MainActivity.floorDisplayed);
    }
}
