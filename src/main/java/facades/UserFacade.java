package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.Role;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public UserDTO create(UserDTO udto){
        List<Role> roleList = udto.getRoleList().stream().map(r -> new Role(r.getRoleName()) ).collect(Collectors.toList());
        User u = new User(udto.getUserName(), udto.getUserPass(),roleList);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(u);
    }

    //this method deletes a user
    public UserDTO delete(String username){
        EntityManager em = getEntityManager();
        User u = em.find(User.class, username);
        try {
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(u);
    }
    public List<UserDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> persons = query.getResultList();
        return UserDTO.getDtos(persons);
    }

    public UserDTO getUserByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, userName);
            return new UserDTO(user);
        } finally {
            em.close();
        }
    }

//    //This method shows all the previous transactions of a user and their current account status
//    public UserDTO getTransactions(String username){
//        EntityManager em = emf.createEntityManager();
//        User u = em.find(User.class, username);
//        return new UserDTO(u);
//    }
//
//    //This method allows a user to assign their family to an event
////This method shows all the previous transactions of a member and current account status
//    public String getMemberTransactions(int id) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            return GSON.toJson(em.createQuery("SELECT m FROM Member m WHERE m.id = :id")
//                    .setParameter("id", id)
//                    .getSingleResult());
//        } finally {
//            em.close();
//        }
//    }
//
//    //This method allows a member to assign their family for an event
//    public String assignFamily(int id, int familyName) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createQuery("UPDATE Member m SET m.familyName = :familyId WHERE m.id = :id")
//                    .setParameter("familyId", familyName)
//                    .setParameter("id", id)
//                    .executeUpdate();
//            em.getTransaction().commit();
//            return GSON.toJson(em.createQuery("SELECT m FROM Member m WHERE m.id = :id")
//                    .setParameter("id", id)
//                    .getSingleResult());
//        } finally {
//            em.close();
//        }


}
