package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }


    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



}
