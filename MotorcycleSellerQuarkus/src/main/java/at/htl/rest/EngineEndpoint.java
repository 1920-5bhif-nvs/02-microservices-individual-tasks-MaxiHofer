package at.htl.rest;

import at.htl.model.Engine;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/engine")
public class EngineEndpoint {

    @Inject
    EntityManager em;

    @POST
    @Path("/insertEngine")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEngine(Engine engine) {
        em.persist(engine);
        return Response.ok().entity(engine).build();
    }

    @GET
    @Path("/getEngines")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Engine> getEngines() {
        TypedQuery<Engine> query = em.createNamedQuery("Engine.findAll",Engine.class);
        return query.getResultList();
    }

    @GET
    @Path("/getEngine/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Engine getEngineById(@PathParam("id") long id) {
        return em.find(Engine.class, id);
    }

    @PUT
    @Path("/updateEngine/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEngine(@PathParam("id") long id, Engine e) {
        if(e == null || em.find(Engine.class, id) == null) {
            return Response.serverError().build();
        }
        e.setId(id);
        em.merge(e);
        return Response.ok().entity(em.find(Engine.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteEngine/{id}")
    public void deleteEngine(@PathParam("id") long id) {
        Engine e = em.find(Engine.class, id);
        if(e != null) {
            em.remove(em.contains(e) ? e : em.merge(e));
        }
    }
}
