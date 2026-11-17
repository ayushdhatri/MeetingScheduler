package strategies;

import models.Room;

import java.util.Comparator;
import java.util.List;

public class BestFitRoomSelectionStrategy implements RoomSelectionStrategy{

    /*
    * It will choose a room with just satisfying requiredCapacity
    * make sure to implement comparator
     */
    @Override
    public Room selectRoom(List<Room> availableRooms, int requiredCapacity) {
        Comparator<Room> roomComparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return Integer.compare(o1.getCapacity(), o2.getCapacity());
            }
        };

        return availableRooms.stream().filter((room)->{
            return room.getCapacity() >= requiredCapacity;
        }).sorted(roomComparator).findFirst().orElse(null);
    }
}
