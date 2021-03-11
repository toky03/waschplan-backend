package ch.bfh.boundary;

import ch.bfh.control.FcmService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Set;

@Path("/register")
public class FcmResource {

    @Inject
    private FcmService fcmService;

    @Path("{registrationId}")
    @POST
    public void addRegistrationId(@PathParam("registrationId") String id){
        fcmService.addRegistrationId(id);
    }

    @Path("{registrationId}")
    @DELETE
    public void removeRegistrationId(String id){
        fcmService.removeRegistrationId(id);
    }

    @Path("{oldId}/{newId}")
    @PUT
    public void replaceRegistrationId(@PathParam("oldId")String oldId, @PathParam("newId")String newId){
        fcmService.replaceRegistrationId(oldId, newId);
    }

    @GET
    public Set<String> readAllRegisteredIds(){
        return fcmService.getRegisteredIds();
    }

}
