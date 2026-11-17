package strategies;

import models.Room;

import java.util.List;

public class FirstAvailableRoomSelectionStrategy implements RoomSelectionStrategy{

    @Override
    public Room selectRoom(List<Room> availableRooms, int requiredCapacity) {
       return availableRooms.isEmpty() ? null : availableRooms.getFirst();
    }
}
