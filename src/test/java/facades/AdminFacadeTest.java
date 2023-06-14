package facades;

import dtos.EventDTO;
import entities.Event;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Uncomment the line below, to temporarily disable this test
//@Disabled
public class AdminFacadeTest {

    private static EntityManagerFactory emf;
    private static AdminFacade facade;

    public AdminFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AdminFacade.getAdminFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up database after the test class is done or use a persistence unit with drop-and-create to start up clean on every test
        emf.close();
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Event.deleteAll").executeUpdate();
            // String time, String location, String dish, int pricePerPerson
            em.persist(new Event("20-08-2024", "Copenhagen", "Pizza", 15));
            em.persist(new Event("28-08-2024", "Paris", "Pasta", 15));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        // Remove any data after each test is run
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Event.deleteAll").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    //This method tests the updateEvent method
    @Test
    public void testUpdateEvent() throws Exception {
        // Get the initial number of events
        int initialCount = facade.getAllEvents().size();

        // Update an event
        EventDTO updatedEvent = facade.updateEvent(new EventDTO(1L, "20-08-2024", "Copenhagen", "Pizza", 15));

        // Verify that the updated event is returned
        assertEquals("Copenhagen", updatedEvent.getLocation());
        assertEquals("Pizza", updatedEvent.getDish());
        assertEquals(15, updatedEvent.getPricePerPerson());

        // Verify that the number of events is unchanged
        assertEquals(initialCount, facade.getAllEvents().size());

        // Try to find the updated event in the database
        EntityManager em = emf.createEntityManager();
        Event foundEvent = em.find(Event.class, 1L);
        em.close();

        // Verify that the updated event is found in the database
        assertEquals("Copenhagen", foundEvent.getLocation());
        assertEquals("Pizza", foundEvent.getDish());
        assertEquals(15, foundEvent.getPricePerPerson());
    }

    // Test the deleteEvent method
    @Test
    public void testDeleteEvent() throws Exception {
        // Get the initial number of events
        int initialCount = facade.getAllEvents().size();

        // Delete an event
        EventDTO deletedEvent = facade.deleteEvent(1L);

        // Verify that the deleted event is returned
        assertEquals("Paris", deletedEvent.getLocation());
        assertEquals("Pasta", deletedEvent.getDish());
        assertEquals(15, deletedEvent.getPricePerPerson());

        // Verify that the event is removed from the database
        assertEquals(initialCount - 1, facade.getAllEvents().size());

        // Try to find the deleted event in the database
        EntityManager em = emf.createEntityManager();
        Event foundEvent = em.find(Event.class, 1L);
        em.close();

        // Verify that the deleted event is not found in the database
        assertNull(foundEvent);
    }


    //This method tests the createEvent method
    @Test
    public void testCreateEvent() throws Exception {
        // Get the initial number of events
        int initialCount = facade.getAllEvents().size();

        // Create an event
        EventDTO createdEvent = facade.createEvent(new EventDTO("20-08-2024", "Copenhagen", "Pizza", 15));

        // Verify that the created event is returned
        assertEquals("Copenhagen", createdEvent.getLocation());
        assertEquals("Pizza", createdEvent.getDish());
        assertEquals(15, createdEvent.getPricePerPerson());

        // Verify that the number of events is increased by one
        assertEquals(initialCount + 1, facade.getAllEvents().size());

        // Try to find the created event in the database
        EntityManager em = emf.createEntityManager();
        Event foundEvent = em.find(Event.class, createdEvent.getId());
        em.close();

        // Verify that the created event is found in the database
        assertEquals("Copenhagen", foundEvent.getLocation());
        assertEquals("Pizza", foundEvent.getDish());
        assertEquals(15, foundEvent.getPricePerPerson());
    }
}
