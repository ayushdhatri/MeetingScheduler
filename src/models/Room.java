package models;

import enums.RoomType;
import util.IdGenerator;

public class Room {
    private final String id;

    private RoomType type;

    private final String name;

    private int capacity;
    public Room(String name, RoomType type, int capacity){
        this.id = IdGenerator.generateId();
        this.type = type;
        this.name = name;
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

    public String getName(){
        return this.name;
    }



}
