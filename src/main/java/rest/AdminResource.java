package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import facades.AdminFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("admin/events")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final AdminFacade FACADE = AdminFacade.getAdminFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // US-4: As an admin, I would like to create a new event
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(EventDTO eventDTO) {
        // Create a new event in the database
        // Your implementation here

        return Response.status(Response.Status.CREATED).build();
    }

    // US-5: As an admin, I would like to remove members from an event
    @DELETE
    @Path("/{eventId}/members/{memberId}")
    public Response removeMemberFromEvent(@PathParam("eventId") String eventId, @PathParam("memberId") String memberId) {
        // Remove member from the event
        // Your implementation here

        return Response.ok().build();
    }

    // US-6: As an admin, I would like to update all information about an event
    @PUT
    @Path("/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvent(@PathParam("eventId") String eventId, EventDTO eventDTO) {
        // Update event information in the database
        // Your implementation here

        return Response.ok().build();
    }

    // US-7: As an admin, I would like to delete an event
    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@PathParam("eventId") String eventId) {
        // Delete the event from the database
        // Your implementation here

        return Response.ok().build();
    }
}
