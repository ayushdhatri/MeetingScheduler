package strategies;

import models.Room;

import java.util.List;

/*
* Here we passed the pre-filtered available rooms along with capacity
* We did not pass list of room because it would have duplicate the work of filtering room that does clash with meeting times in everyStrategy
* Hence we prefiltered this and pass to interface which can select rooms based on the capacity accordingly like best fit or first fit
 */
public interface RoomSelectionStrategy {
    Room selectRoom(List<Room> availableRooms,int requiredCapacity);
}
