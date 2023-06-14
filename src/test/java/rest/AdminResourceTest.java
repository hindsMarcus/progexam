package rest;

import dtos.UserDTO;
import entities.Event;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AdminResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static User u1, u2;
    private static Event event1, event2;
    List<Role> userList = new ArrayList<>();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        //String name, String time, String location, String duration, String date, String packingList, Guide guide
        event1 = new Event("20-08-2024", "Copenhagen", "Pizza", 15);
        event2 = new Event("14-12-2024", "Paris", "Snails", 30);


        User user = new User("user", "test123", "Duehusvej 1", "10101010", "mail1", 1999, 2000);
        User admin = new User("admin", "test123", "Duehusvej 2", "20202020", "mail2", 1998, 1500);
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Event.deleteAll").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(event1);
            em.persist(event2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    public void testCreateEvent() {
        Event event = new Event("20-08-2024", "Copenhagen", "Pizza", 15);
        given()
                .contentType("application/json")
                .body(event)
                .post("/admin/create")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    //This rest endpoint test, tests the endpoint for deleting an event
    @Test
    public void testDeleteEvent() {
        given()
                .contentType("application/json")
                .delete("/admin/delete/" + event1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    //This rest endpoint test, tests the endpoint for editing an event
    @Test
    public void testEditEvent() {
        event1.setLocation("Paris");
        event1.setDish("Pasta");
        given()
                .contentType("application/json")
                .body(event1)
                .put("/admin/update/" + event1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }


}
