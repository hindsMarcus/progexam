package dtos;

import java.time.LocalDateTime;

public class EventDTO {

    private String id;
    private LocalDateTime time;
    private String location;
    private String dish;
    private double pricePerPerson;

    public EventDTO() {
    }

    public EventDTO(String id, LocalDateTime time, String location, String dish, double pricePerPerson) {
        this.id = id;
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePerPerson = pricePerPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", dish='" + dish + '\'' +
                ", pricePerPerson=" + pricePerPerson +
                '}';
    }
}
