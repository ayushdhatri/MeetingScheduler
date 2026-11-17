package models;

import enums.RoomType;
import util.IdGenerator;

public class Room {
    private final String id;

    private RoomType type;

    public Room(){
        this.id = IdGenerator.generateId();

    }


}
