//package facades;
//
//import dtos.EventDTO;
//import entities.Event;
//import org.junit.jupiter.api.*;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//// Uncomment the line below, to temporarily disable this test
////@Disabled
//public class EventFacadeTest {
//
//    private static EntityManagerFactory emf;
//    private static EventFacade facade;
//
//    public EventFacadeTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//        facade = EventFacade.getEventFacade(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//        // Clean up database after the test class is done or use a persistence unit with drop-and-create to start up clean on every test
//        emf.close();
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Event.deleteAll").executeUpdate();
//            // String time, String location, String dish, int pricePerPerson
//            em.persist(new Event("20-08-2024", "Copenhagen", "Pizza", 15));
//            em.persist(new Event("28-08-2024", "Paris", "Pasta", 15));
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Remove any data after each test is run
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Event.deleteAll").executeUpdate();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    // Test the getAllEvents method
//    @Test
//    public void testGetAllEvents() throws Exception {
//        assertEquals(2, facade.getAllEvents().size(), "Expects two rows in the database");
//    }
//
//}
