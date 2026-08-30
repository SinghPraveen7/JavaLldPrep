package org.practice.design.practice.movie_booking_system;

//screen (screenid, cinemaId, name)
public class Screen {

    private String screenId;
    private String cinemaId;
    private String name;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
