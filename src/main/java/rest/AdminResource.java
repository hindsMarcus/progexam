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
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(String input) {
        EventDTO eventDTO = GSON.fromJson(input, EventDTO.class);
        FACADE.createEvent(eventDTO);
        return Response.ok().build();
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update/{eventId}")
    public Response updateEvent(@PathParam("eventId") Long eventId, String input) {
        EventDTO eventDTO = GSON.fromJson(input, EventDTO.class);
        eventDTO.setId(eventId);
        EventDTO updatedEventDTO = FACADE.updateEvent(eventDTO);
        return Response.ok(updatedEventDTO).build();
    }


    // US-7: As an admin, I would like to delete an event
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete/{eventId}")
    public Response deleteEvent(@PathParam("eventId") Long eventId) {
        FACADE.deleteEvent(eventId);
        return Response.ok().build();
    }
}
