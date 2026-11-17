package models;

import enums.MeetingStatus;
import util.IdGenerator;

import java.util.List;
import java.util.UUID;

public class Meeting {
    private final String id;

    private User organizer;

    private List<User> participants;

    private MeetingStatus status;

    private final TimeSlot slot;

    private final Room room;

    public Meeting(User organizer, List<User> participants, TimeSlot slot, Room room){
        this.id = IdGenerator.generateId();
        this.organizer = organizer;
        this.participants = participants;
        this.status = MeetingStatus.ACTIVE;
        this.slot = slot;
        this.room = room;
    }


    public String getId(){
        return this.id;
    }

    public User getOrganizer(){
        return this.organizer;
    }

    public List<User> getParticipants(){
        return this.participants;
    }

    public MeetingStatus getStatus(){
        return this.status;
    }

    public void addParticipants(User participant){
        this.participants.add(participant);
    }





}
