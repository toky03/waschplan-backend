package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;
import ch.bfh.dto.ErrorMessage;
import ch.bfh.dto.ReplacedId;
import ch.bfh.dto.Termin;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ConcurrentModificationException;
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
        try{
            String newId = waschplanService.createTermin(termin);
            ReplacedId replacedId = ReplacedId.builder().newId(newId).oldId(termin.getId()).build();
            return Response.status(Response.Status.CREATED).entity(replacedId).type(MediaType.APPLICATION_JSON).build();
        } catch (WebApplicationException ex){
            return Response.status(ex.getResponse().getStatus()).entity(ErrorMessage.builder().message(ex.getMessage()).build()).type(MediaType.APPLICATION_JSON).build();
        }


    }

    @Path("{terminId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBuchung(@PathParam("terminId") UUID terminId, Termin termin) {
        try {
            waschplanService.updateTermin(terminId, termin);
            return Response.status(Response.Status.CREATED).build();
        } catch (WebApplicationException ex) {
            return Response.status(ex.getResponse().getStatus()).entity(ErrorMessage.builder().message(ex.getMessage()).build()).type(MediaType.APPLICATION_JSON).build();
        }
    }


    @Path("{terminId}")
    @DELETE
    public void deleteBuchung(@PathParam("terminId") UUID terminId) {
        waschplanService.deleteTermin(terminId);
    }
}