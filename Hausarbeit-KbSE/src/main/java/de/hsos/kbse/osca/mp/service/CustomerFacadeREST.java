/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hs.kbse.osca.mp.RESTfulInterfaces.CustomerRestInterface;
import de.hsos.kbse.osca.mp.controller.CustomerRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nordm
 */
@RequestScoped
@Path("de.hsos.kbse.osca.mp.entity.customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> implements CustomerRestInterface {

    @Inject
    private CustomerRepository repo;

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Customer> findAllStudents() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByType", Customer.class);
        query.setParameter("type", AccessType.STUDENT.getLevelCode());
        return query.getResultList();
    }

    /**
     *
     * @return
     */
    @Override
    public Response findAllDozents() {
        try {
            Collection<Customer> all = repo.findAllDozents();
            if (all.isEmpty()) {
                return Response.noContent().build();
            }
            return Response
                    .status(Response.Status.CREATED)
                    .entity(getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

    /**
     *
     * @param firstname
     * @param email
     * @return
     */
    @Override
    public Response createStudent(String firstname, String email) {
        Customer cus = new Customer(firstname, "lastname", email, "login", "password", 2);
        try {
            super.create(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Customer entity
    ) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id
    ) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Long id
    ) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findRange(@PathParam("from") Integer from,
            @PathParam("to") Integer to
    ) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
