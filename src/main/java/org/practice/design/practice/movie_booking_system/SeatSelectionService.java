package org.practice.design.practice.movie_booking_system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatSelectionService {

    private LockService lockService;

    private Map<String, List<Seat>> showWiseSeatMapping;
    private Map<String, String> userLockMapping;

    public SeatSelectionService() {
        showWiseSeatMapping = new HashMap<>();    // will fetch from repo layer
    }

    public List<Seat> fetchShowSeats(Show show) {
        return showWiseSeatMapping.get(show.getShowId());
    }

    public synchronized boolean lockSeats(List<Seat> selectedSeat, String userId, Show show) {
        long ttl = 10 * 60 * 1000;
        String lockId = lockService.lockSeat(selectedSeat, userId, show.getShowId(), ttl);
        if (lockId == null) {
            System.out.println("Can't proceed with selected seats, already selected by other user!");
            return false;
        }
        userLockMapping.put(userId, lockId); // Will keep locked seat reference if needed to check what user selected
        return true;
    }

    public synchronized boolean isSeatLocked(List<Seat> selectedSeat, String userId, Show show) {
        return lockService.isSeatLocked(selectedSeat, userId, show.getShowId());
    }

    public void unlockSeats(List<Seat> bookedSeats, String userId, Show show) {
        lockService.unlockSeat(bookedSeats, userId, show.getShowId());
    }
}
