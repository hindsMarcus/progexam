package dtos;

import entities.Event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDTO {

    private Long id;
    private String time;
    private String location;
    private String dish;
    private double pricePerPerson;


    public EventDTO(Long id, String time, String location, String dish, double pricePerPerson) {
        this.id = id;
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePerPerson = pricePerPerson;
    }

    public EventDTO(String time, String location, String dish, double pricePerPerson) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePerPerson = pricePerPerson;
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.time = event.getTime();
        this.location = event.getLocation();
        this.dish = event.getDish();
        this.pricePerPerson = event.getPricePerPerson();
    }


    //This method gets a list of Event entities and returns a list of EventDTOs
    public static List<EventDTO> getEventDTOs(List<Event> events) {
        ArrayList<EventDTO> eventDTOS = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO = new EventDTO(event.getId(), event.getTime(), event.getLocation(), event.getDish(), event.getPricePerPerson());
            System.out.println(event);
            eventDTOS.add(eventDTO);
        }
        return eventDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
