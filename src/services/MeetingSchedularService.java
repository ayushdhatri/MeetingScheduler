package services;

import enums.MeetingStatus;
import exceptions.MeetingSchedularException;
import models.Meeting;
import models.Room;
import models.TimeSlot;
import models.User;
import observers.MeetingObserver;
import strategies.FirstAvailableRoomSelectionStrategy;
import strategies.RoomSelectionStrategy;

import java.sql.Array;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MeetingSchedularService {
    private static volatile MeetingSchedularService instance;
    private static final Object lock = new Object();
    private Map<String, Room> rooms;// this has all the rooms
    private Map<String, List<Meeting>> roomMeetings;
    private Map<String, Meeting> meetings;
    private RoomSelectionStrategy roomSelectionStrategy;
    private CopyOnWriteArrayList<MeetingObserver> observers;

    private MeetingSchedularService(){
        this.rooms = new ConcurrentHashMap<>();
        this.roomMeetings = new ConcurrentHashMap<>();
        this.meetings = new ConcurrentHashMap<>();
        this.roomSelectionStrategy = new FirstAvailableRoomSelectionStrategy();
        this.observers = new CopyOnWriteArrayList<>();
    }

    MeetingSchedularService getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new MeetingSchedularService();
                }
            }
        }
        return instance;
    }

    public synchronized void addRoom(Room newRoom){
        rooms.put(newRoom.getId(), newRoom);
        roomMeetings.putIfAbsent(newRoom.getId(), new ArrayList<>());
    }


    public synchronized Meeting scheduleMeeting(String subject, User organizer, List<User> participants, TimeSlot slot, int capacity){

        List<Room> availbleRooms = getAvailableRooms(slot, capacity);
        Room roomForMeeting = roomSelectionStrategy.selectRoom(availbleRooms, capacity);

        if(roomForMeeting == null){
            throw new MeetingSchedularException("No available room found for capacity " + capacity + " during " + slot);
        }

        Meeting meeting = new Meeting(subject,organizer,participants,slot,roomForMeeting);

        roomMeetings.computeIfAbsent(roomForMeeting.getId(),(key)-> new ArrayList<>()).add(meeting);

        meetings.putIfAbsent(meeting.getId(), meeting);

        notifyMeetingScheduled(meeting);

        return meeting;

    }

    public synchronized void cancelMeeting(String meetingId){
        Meeting meeting = meetings.getOrDefault(meetingId, null);
        if(meeting == null){
            throw new MeetingSchedularException("Meeting not found " + meetingId);
        }

        meeting.cancel();

        roomMeetings.get(meeting.getRoom().getId()).remove(meeting);

        notifyMeetingCancelled(meeting);
    }

    public List<Room> getAvailableRooms(TimeSlot slot, int capacity){
        List<Room> available = new ArrayList<>();
        for(Room room : rooms.values()){
            if(room.getCapacity() < capacity)
                continue;
            // now we will check if rooms have any conflicitin meeting
            List<Meeting> existingMeetings = roomMeetings.getOrDefault(room.getId(), Collections.emptyList());
            boolean hasConflict = existingMeetings.stream().anyMatch((meeting -> {
                return slot.overlaps(meeting.getSlot());
            }));
            if(!hasConflict){
                available.add(room);
            }
        }
        return available;

    }


    public synchronized void setRoomSelectionStrategy(RoomSelectionStrategy strategy){
        this.roomSelectionStrategy = strategy;
    }


    public void addObserver(MeetingObserver observer){
        observers.add(observer);
    }

    private void notifyMeetingScheduled(Meeting meeting){
        for(MeetingObserver observer : observers){
            try{
                observer.onMeetingScheduled(meeting);
            }
            catch(Exception ex){
                System.out.println("Observer notification failed " + ex.getMessage());
            }
        }
    }

    public void notifyMeetingCancelled(Meeting meeting){
        for(MeetingObserver observer : observers){
            try{
                observer.onMeetingCancelled(meeting);
            }
            catch(Exception ex){
                System.out.println("Observer notification failed ");
            }
        }
    }


}
