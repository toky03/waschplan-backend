package ch.bfh.boundary;

import ch.bfh.control.WaschplanService;
import ch.bfh.dto.Mieter;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Mieter", description = "Api für Mieter/Benutzer", version = "1.0.0"))
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
