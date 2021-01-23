package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;
import ch.bfh.dto.Mieter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mieter")
public class MieterResource {

    @Inject
    private WaschplanService waschplanService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mieter> readMieter() {
        return waschplanService.readMieter();
    }

}
