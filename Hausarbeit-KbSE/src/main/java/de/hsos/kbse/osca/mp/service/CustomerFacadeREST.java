/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hsos.kbse.osca.mp.entity.Customer;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCustomer( 
                        @QueryParam("firstname") String firstname,
                        @QueryParam("lastname") String lastname
            ) {
        Customer cus = new Customer(firstname, lastname, "lastname", "lastname", "lastname", 2);
        try {
            super.create(cus);

            return Response
                    .status(200)
                    .entity("newEntity : " + cus.getFirstname()+ " with " + cus.getLastname()
                            + " and " + cus.getLogin() + " and " + cus.getPassword()).build();
        } catch (NullPointerException | IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();

        }
    }

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Customer entity) {
//        super.create(entity);
//    }
        @PUT
        @Path("{id}")
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public void edit
        (@PathParam("id")
        Long id, Customer entity
        
            ) {
        super.edit(entity);
        }

        @DELETE
        @Path("{id}")
        public void remove
        (@PathParam("id")
        Long id
        
            ) {
        super.remove(super.find(id));
        }

        @GET
        @Path("{id}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public Customer find
        (@PathParam("id")
        Long id
        
            ) {
        return super.find(id);
        }

        @GET
        @Override
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<Customer> findAll
        
            () {
        return super.findAll();
        }

        @GET
        @Path("students/")
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<Customer> findAllStudents
        
            () {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByType", Customer.class);
            query.setParameter("type", AccessType.STUDENT.getLevelCode());
            return query.getResultList();
        }

        @GET
        @Path("dozent/")
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<Customer> findAllDozents
        
            () {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByType", Customer.class);
            query.setParameter("type", AccessType.DOZENT.getLevelCode());
            return query.getResultList();
        }

        @GET
        @Path("{from}/{to}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<Customer> findRange
        (@PathParam("from")
        Integer from, 
        @PathParam("to") Integer to
        
            ) {
        return super.findRange(new int[]{from, to});
        }

        @GET
        @Path("count")
        @Produces(MediaType.TEXT_PLAIN)
        public String countREST
        
            () {
        return String.valueOf(super.count());
        }

        @Override
        protected EntityManager getEntityManager
        
            () {
        return em;
        }

    }
