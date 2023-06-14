package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import facades.MemberFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("members")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final MemberFacade FACADE = MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // US-2: As a member, I would like to see all my previous transactions and my current account status
//    @GET
//    @Path("/{memberId}/transactions")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getMemberTransactions(@PathParam("memberId") String memberId) {
//        // Retrieve member transactions from the database
//        List<> transactions =
//
//        return Response.ok(transactions).build();
//    }

//    @GET
//    @Path("/{memberId}/account-status")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getMemberAccountStatus(@PathParam("memberId") String memberId) {
//        // Retrieve member account status from the database
//        AccountStatusDTO accountStatus = // Your implementation here
//
//        return Response.ok(accountStatus).build();
//    }

    // US-3: As a member, I would like to assign my family to an event and add members
    @POST
    @Path("/{memberId}/assignments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignFamilyToEvent(@PathParam("memberId") String memberId, AssignmentDTO assignmentDTO) {
        // Create assignment and add members to the event
        // Your implementation here

        return Response.status(Response.Status.CREATED).build();
    }
}
