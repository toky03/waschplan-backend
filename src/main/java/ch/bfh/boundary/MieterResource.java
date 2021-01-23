package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/mieter")
public class MieterResource {

    @Inject
    private WaschplanService waschplanService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String readMieter() {
        return waschplanService.readMieter();
    }

}
