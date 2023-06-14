package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        return null;
    }



}
