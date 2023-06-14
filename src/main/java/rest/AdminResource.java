package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import entities.Event;
import facades.AdminFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("admin")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final AdminFacade FACADE = AdminFacade.getAdminFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // US-4: As an admin, I would like to create a new event
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(EventDTO eventDTO) {
        FACADE.createEvent(eventDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    // US-5: As an admin, I would like to remove users from an event
//    @DELETE
//    @Path("/{eventId}/users/{userId}")
//    public Response removeUserFromEvent(@PathParam("eventId") int eventId, @PathParam("userId") String userId) {
//            FACADE.removeUserFromEvent(eventId, userId);
//            return Response.ok().build();
//}

    // US-6: As an admin, I would like to update all information about an event
    @PUT
    @Path("/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvent(@PathParam("eventId") Long eventId, EventDTO eventDTO) {
        eventDTO.setId(eventId);
        EventDTO updatedEventDTO = FACADE.updateEvent(eventDTO);
        return Response.ok(updatedEventDTO).build();
    }


    // US-7: As an admin, I would like to delete an event
    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@PathParam("eventId") int eventId) {
        EventDTO eventDTO = FACADE.deleteEvent(eventId);
        return Response.ok(eventDTO).build();
    }
}
