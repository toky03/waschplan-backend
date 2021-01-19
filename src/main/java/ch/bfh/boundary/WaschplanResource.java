package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;
import ch.bfh.dto.Buchung;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/waschplan")
public class WaschplanResource {

    @Inject
    private WaschplanService waschplanService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buchung> readBuchungen() {
        return waschplanService.readBuchungen();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createBuchung(Buchung buchung){
        waschplanService.createBuchung(buchung);
    }

    @Path("{buchungId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBuchung(@PathParam("buchungId") UUID buchungId, Buchung buchung){
        waschplanService.updateBuchung(buchungId, buchung);
    }

    @Path("{buchungId}")
    @DELETE
    public void deleteBuchung(@PathParam("buchungId") UUID buchungId) {
        waschplanService.deleteBuchung(buchungId);
    }
}