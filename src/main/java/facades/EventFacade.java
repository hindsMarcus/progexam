package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import entities.Assignment;
import entities.Event;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class EventFacade {

    private static EventFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EventFacade() {
    }


    public static EventFacade getEventFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EventFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //This method gets a list of all events from the database
    public List<EventDTO> getAllEvents() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e", Event.class);
        List<Event> events = query.getResultList();
        em.close();

        return EventDTO.getEventDTOs(events);
    }

    //This method adds and event to an assignment
    public void addEventToAssignment(int eventId, int assignmentId) {
        EntityManager em = emf.createEntityManager();
        Event event = em.find(Event.class, eventId);
        Assignment assignment = em.find(Assignment.class, assignmentId);
        assignment.addEvent(event);
        try {
            em.getTransaction().begin();
            em.merge(assignment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    //This method adds a user to an assignment
    public void addUserToAssignment(int assignmentId, String userName) {
        EntityManager em = emf.createEntityManager();
        Assignment assignment = em.find(Assignment.class, assignmentId);
        User user = em.find(User.class, userName);
        assignment.addUser(user);
        try {
            em.getTransaction().begin();
            em.merge(assignment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
