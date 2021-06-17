package com.example.tlu.base;

import com.example.tlu.MainActivity;

public class FindDestination implements Runnable{
    @Override
    public void run() {
        MainActivity.destinationHolder = com.example.tlu.MainActivity.db.DatabaseDao().findDestination(MainActivity.roomIDForDestination);
    }
}
