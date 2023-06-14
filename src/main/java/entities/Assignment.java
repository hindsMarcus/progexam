package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;

    @Column(name = "date")
    private String date;

    @ManyToOne
    private Event event;

    @ManyToMany
    @JoinTable(name = "assignment_users",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "users_user_name"))
    private List<User> users = new ArrayList<>();


    public Assignment() {
    }

    public Assignment(String familyName, String createDate, String contactInfo, Event event, List<User> users) {
        this.familyName = familyName;
        this.date = createDate;
        this.contactInfo = contactInfo;
        this.event = event;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Event getEvent() {
        return event;
    }

    public void addEvent(Event event) {
        this.event = event;
    }

}