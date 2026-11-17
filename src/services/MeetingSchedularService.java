package services;

import models.Meeting;
import models.Room;
import models.TimeSlot;
import models.User;

import java.sql.Array;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingSchedularService {
    private static volatile MeetingSchedularService instance;

    private static final Object lock = new Object();
    private Map<String, Room> rooms;// this has all the rooms
    private Map<String, List<Meeting>> roomMeetings;


    private MeetingSchedularService(){
        this.rooms = new ConcurrentHashMap<>();
        this.roomMeetings = new ConcurrentHashMap<>();
    }

    public MeetingSchedularService getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new MeetingSchedularService();
                }
            }
        }
        return instance;
    }

    public Meeting scheduleMeeting(String subject, User organizer, List<User> participants, TimeSlot slot, int capacity){

        List<Room> availbleRooms = getAvailableRooms(slot, capacity);
    }

    public List<Room> getAvailableRooms(TimeSlot slot, int capacity){
        List<Room> available = new ArrayList<>();
        for(Room room : rooms.values()){
            if(room.getCapacity() < capacity)
                continue;
            // now we will check if rooms have any conflicitin meeting
            List<Meeting> existingMeetings = roomMeetings.getOrDefault(room.getId(), Collections.emptyList());
            boolean hasConflict = existingMeetings.stream().anyMatch((meeting -> {
                if(slot.overlaps(meeting.getSlot()))return true;
                return false;
            }));
            if(!hasConflict){
                available.add(room);
            }
        }

    }


}
