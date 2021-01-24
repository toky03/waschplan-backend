package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;
import ch.bfh.dto.Termin;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(info = @Info(title = "Termine", description = "Api für Waschplan Termine", version = "1.0.0"))
@Path("/termine")
public class WaschplanResource {

    @Inject
    private WaschplanService waschplanService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Termin> readBuchungen() {
        return waschplanService.readTermine();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBuchung(Termin termin) {
        try {
            waschplanService.createTermin(termin);
            return Response.status(Response.Status.CREATED).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    @Path("{terminId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBuchung(@PathParam("terminId") UUID terminId, Termin termin) {
        try {
            waschplanService.updateTermin(terminId, termin);
            return Response.status(Response.Status.CREATED).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }


    @Path("{terminId}")
    @DELETE
    public void deleteBuchung(@PathParam("terminId") UUID terminId) {
        waschplanService.deleteTermin(terminId);
    }
}