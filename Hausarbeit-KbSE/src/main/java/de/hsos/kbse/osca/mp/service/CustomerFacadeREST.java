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
import javax.persistence.NoResultException;
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
public class CustomerFacadeREST implements CustomerRestInterface {

    @Inject
    private CustomerRepository repo;

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacadeREST() {
//        super(Customer.class);
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
                    .entity(this.repo.getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
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
                    .entity(this.repo.getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
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
                    .entity(this.repo.getJsonb().toJson(all)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
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
    public Response createStudent(String firstname, String lastname, String email, String login, String password) {
        try {
            Customer cus = new Customer(firstname, lastname, email, login, password, AccessType.STUDENT.getLevelCode());
            this.repo.create(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
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
    public Response createDozent(String firstname, String lastname, String email, String login, String password) {
        try {
            Customer cus = new Customer(firstname, lastname, email, login, password, AccessType.DOZENT.getLevelCode());
            this.repo.create(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
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
    public Response createAdmin(String firstname, String lastname, String email, String login, String password) {
        try {
            Customer cus = new Customer(firstname, lastname, email, login, password, AccessType.ADMINISTRATOR.getLevelCode());
            this.repo.create(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomer(String login, String newLogin, String firstname, String lastname, String email, String password) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setFirstname(firstname);
            cus.setEmail(email);
            cus.setLastname(lastname);
            cus.setPassword(password);
            cus.setLogin(newLogin);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomerLogin(String login, String newLogin) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setLogin(newLogin);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomerFirstname(String login, String firstname) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setFirstname(firstname);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomerLastname(String login, String lastname) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setLastname(lastname);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomerEmail(String login, String email) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setEmail(email);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateCustomerPassword(String login, String password) {
        try {
            Customer cus = this.repo.getByLogin(login);
            cus.setPassword(password);
            this.repo.edit(cus);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response findCustomerByLogin(String login) {
        try {
            Customer cus = this.repo.getByLogin(login);
            return Response
                    .status(Response.Status.FOUND)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response deleteCustomer(String login) {
        try {
            Customer cus = this.repo.getByLogin(login);
            this.repo.remove(cus);
            return Response
                    .status(Response.Status.GONE)
                    .entity(this.repo.getJsonb().toJson(cus)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Customer entity) {
        this.repo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.repo.remove(this.repo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Long id
    ) {
        return this.repo.find(id);
    }

    @GET
//    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return this.repo.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findRange(@PathParam("from") Integer from,
            @PathParam("to") Integer to
    ) {
        return this.repo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(this.repo.count());
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
