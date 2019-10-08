package at.htl;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/motorcycle")
public class MotorcycleResource {
    @Inject
    @RestClient
    MotorcycleRequest motorcycleRequest;

    @GET
    @Path("/getMotorcycles")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "requestAmount", description = "Amount of Requests")
    @Timed(name = "responseTimer", description = "Response Time", unit = MetricUnits.MILLISECONDS)
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "noMotorcycles")
    public Response getMotorcycles() {
        return Response.ok().entity(motorcycleRequest.getMotorcycles()).build();
    }

    public Response noMotorcycles() {
        return Response.ok().entity(Json.createArrayBuilder().build()).build();
    }
}
