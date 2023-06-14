package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import facades.EventFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("events")
public class EventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final EventFacade FACADE = EventFacade.getEventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // US-1: As a member, I would like to see all events
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEvents() {
        // Retrieve all events from the database
        List<EventDTO> events = FACADE.getAllEvents();

        return Response.ok(events).build();
    }
}
