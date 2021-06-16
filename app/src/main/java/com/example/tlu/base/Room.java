package com.example.tlu.base;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Fts4
@Entity(tableName = "room"/*,
        foreignKeys = {@ForeignKey(entity = Building.class, parentColumns = "id", childColumns = "building_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Floor.class, parentColumns = "id", childColumns = "floor_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RoomName.class, parentColumns = "id", childColumns = "room_name_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RoomType.class, parentColumns = "id", childColumns = "room_type_id", onDelete = ForeignKey.CASCADE)}*/)
public class Room {
    @PrimaryKey
    @ColumnInfo(name = "id"/*, index = true*/)
    public int id;

    @ColumnInfo(name = "code")
    public String code;

    @Nullable
    @ColumnInfo(name = "room_name_id"/*, index = true*/)
    public Integer name;

    @NonNull
    @ColumnInfo(name = "img")
    public String img;

    @ColumnInfo(name = "X")
    public double x;

    @ColumnInfo(name = "Y")
    public double y;

    @ColumnInfo(name = "floor_id"/*, index = true*/)
    public int floorID;

    @ColumnInfo(name = "exists")
    public boolean exists;

    @ColumnInfo(name = "room_type_id"/*, index = true*/)
    public int typeID;

    @ColumnInfo(name = "building_id"/*, index = true*/)
    public int building;

    /*@Ignore
    public Room(int id, String code, int name, String img, int x, int y, int floorID, boolean exists, int typeID, int building){
        this.id = id;
        this.code = code;
        this.name = name;
        this.img = img;
        this.x = x;
        this.y = y;
        this.floorID = floorID;
        this.exists = exists;
        this.typeID = typeID;
        this.building = building;
    }*/

}