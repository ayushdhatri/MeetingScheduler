package models;

import enums.RoomType;
import util.IdGenerator;

public class Room {
    private final String id;

    private RoomType type;

    private int capacity;
    public Room(RoomType type, int capacity){
        this.id = IdGenerator.generateId();
        this.type = type;
        this.capacity = capacity;
    }

    public RoomType getType(){
        return this.type;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String getId(){
        return this.id;
    }



}
