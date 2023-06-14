package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class AdminFacade {

    private static AdminFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AdminFacade() {
    }


    public static AdminFacade getAdminFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AdminFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //This method takes a eventDTO and creates an event in the database
    public EventDTO createEvent(EventDTO eventDTO) {
        EntityManager em = emf.createEntityManager();
        Event event = new Event(eventDTO.getTime(), eventDTO.getLocation(), eventDTO.getDish(), eventDTO.getPricePerPerson());
        try {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EventDTO(event);
    }

    //This method removes a user from an assignment
//    public void removeUserFromEvent(int eventId, String userName) {
//        EntityManager em = emf.createEntityManager();
//        Event event = em.find(Event.class, eventId);
//        event.removeUser(userName);
//        try {
//            em.getTransaction().begin();
//            em.merge(event);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        new EventDTO(event);
//    }


    //This method takes an eventDTO and updates the event in the database
    public EventDTO updateEvent(EventDTO eventDTO) {
        EntityManager em = emf.createEntityManager();
        Event event = em.find(Event.class, eventDTO.getId());
        event.setTime(eventDTO.getTime());
        event.setLocation(eventDTO.getLocation());
        event.setDish(eventDTO.getDish());
        event.setPricePerPerson(eventDTO.getPricePerPerson());
        try {
            em.getTransaction().begin();
            em.merge(event);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EventDTO(event);
    }

    //This method takes an id and deletes the event in the database
    public EventDTO deleteEvent(int id) {
        EntityManager em = emf.createEntityManager();
        Event event = em.find(Event.class, id);
        try {
            em.getTransaction().begin();
            em.remove(event);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EventDTO(event);
    }
}
