package at.htl.rest;

import at.htl.model.Customer;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customer")
public class CustomerEndpoint {
    @Inject
    EntityManager em;

    @POST
    @Path("/insertCustomer")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(Customer customer) {
        em.persist(customer);
        return Response.ok().entity(customer).build();
    }

    @GET
    @Path("getCustomer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("id") long id) {
        return em.find(Customer.class, id);
    }

    @PUT
    @Path("/updateCustomer/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id, Customer c) {
        if(c == null || em.find(Customer.class, id) == null) {
            return Response.serverError().build();
        }
        c.setId(id);
        em.merge(c);
        return Response.ok().entity(em.find(Customer.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteEngine/{id}")
    public void deleteEngine(@PathParam("id") long id) {
        Customer c = em.find(Customer.class, id);
        if(c != null) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
    }
}
