package at.htl.rest;

import at.htl.model.Motorcycle;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/motorcycle")
public class MotorcycleEndpoint {
    @Inject
    EntityManager em;

    @POST
    @Path("/insertMotorcycle")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertMotorcycle(Motorcycle motorcycle) {
        em.persist(motorcycle);
        return Response.ok().entity(motorcycle).build();
    }

    @GET
    @Path("/getMotorcycles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Motorcycle> getMotorcycles() {
        TypedQuery<Motorcycle> query = em.createNamedQuery("Motorcycle.findAll",Motorcycle.class);
        return query.getResultList();
    }

    @GET
    @Path("/getMotorcycle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Motorcycle getMotorcycleById(@PathParam("id") long id) {
        return em.find(Motorcycle.class, id);
    }

    @PUT
    @Path("/updateMotorcycle/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMotorcycle(@PathParam("id") long id, Motorcycle m) {
        if(m == null || em.find(Motorcycle.class, id) == null) {
            return Response.serverError().build();
        }
        m.setId(id);
        em.merge(m);
        return Response.ok().entity(em.find(Motorcycle.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteMotorcycle/{id}")
    public void deleteMotorcycle(@PathParam("id") long id) {
        Motorcycle m = em.find(Motorcycle.class, id);
        if(m != null) {
            em.remove(em.contains(m) ? m : em.merge(m));
        }
    }
}
