package org.practice.design.practice.movie_booking_system;

import java.util.List;

//user (userId, List<Ticket>, name)
public class User {

    private String userId;
    private String name;
    private List<Ticket> bookings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getBookings() {
        return bookings;
    }

    public void setBookings(List<Ticket> bookings) {
        this.bookings = bookings;
    }

}
