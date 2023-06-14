package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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



}
