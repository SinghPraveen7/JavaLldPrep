package org.practice.design.practice.movie_booking_system;

import java.util.List;

/**
 * This service will take care of seat locking for a user basis show for given ttl
 */
public interface LockService {

    String lockSeat(List<Seat> selectedSeat, String userId, String showId, long ttl);

    String unlockSeat(List<Seat> selectedSeat, String userId, String showId);

    boolean isSeatLocked(List<Seat> selectedSeat, String userId, String showId);

}
