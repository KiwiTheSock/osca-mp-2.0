/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hsos.kbse.osca.mp.RESTfulInterfaces.CustomerRestInterface;
import de.hsos.kbse.osca.mp.controller.CustomerRepository;
import de.hsos.kbse.osca.mp.entity.Customer;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public Response findAllStudents() {
        try {
            Collection<Customer> all = repo.findAllStudents();
            if (all.isEmpty()) {
                return Response.noContent().build();
            }
            return Response
                    .status(Response.Status.FOUND)
                    .entity(getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
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
                    .status(Response.Status.FOUND)
                    .entity(getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Response findAllAdmins() {
        try {
            Collection<Customer> all = repo.findAllAdmins();
            if (all.isEmpty()) {
                return Response.noContent().build();
            }
            return Response
                    .status(Response.Status.FOUND)
                    .entity(getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**
     *
     * @param firstname
     * @param lastname
     * @param email
     * @param login
     * @param password
     * @return
     */
    @Override
    public Customer createStudent(String firstname, String lastname, String email, String login, String password) {
        Customer cus = repo.createCustomer(firstname, lastname, email, login, password, AccessType.STUDENT.getLevelCode());
        try {
            super.create(cus);
            return cus;
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     *
     * @param firstname
     * @param lastname
     * @param email
     * @param login
     * @param password
     * @return
     */
    @Override
    public Customer createDozent(String firstname, String lastname, String email, String login, String password) {
        Customer cus = repo.createCustomer(firstname, lastname, email, login, password, AccessType.DOZENT.getLevelCode());
        try {
            super.create(cus);
            return cus;
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     *
     * @param firstname
     * @param lastname
     * @param email
     * @param login
     * @param password
     * @return
     */
    @Override
    public Customer createAdmin(String firstname, String lastname, String email, String login, String password) {
        Customer cus = repo.createCustomer(firstname, lastname, email, login, password, AccessType.ADMINISTRATOR.getLevelCode());
        try {
            super.create(cus);
            return cus;
        } catch (NullPointerException | NotFoundException | IllegalArgumentException ex) {
            return null;
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

    @Override
    public Customer updateCustomer(String login, String firstname, String lastname, String email, String password) {
        return this.getEntityManager().find(Customer.class, login);
    }

    @Override
    public Customer findCustomerByLogin(String login) {
        return this.getEntityManager().find(Customer.class, login);
    }

}
