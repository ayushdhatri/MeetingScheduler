package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeSlot {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public LocalDateTime getEndTime(){
        return this.endTime;
    }

    public boolean overlaps(TimeSlot other){
        return other.startTime.isBefore(this.endTime) && this.startTime.isBefore(other.endTime);
    }



}
