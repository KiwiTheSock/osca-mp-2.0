/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.RESTfulInterfaces;

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
public interface CustomerRestInterface {

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @POST
    @Path("student")
    public Response createStudent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @POST
    @Path("dozent")
    public Response createDozent(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    @POST
    @Path("admin")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAdmin(
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("login") String login,
            @QueryParam("password") String password);

    /**
     *
     * @param login
     * @return
     */
    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerByLogin(@PathParam("login") String login);

    /**
     *
     * @return
     */
    @GET
    @Path("dozents")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllDozents();

    @GET
    @Path("students")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllStudents();

    @GET
    @Path("admins")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllAdmins();

    @PUT
    @Path("update/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomer(
            @PathParam("login") String login,
            @QueryParam("newLogin") String newLogin,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("email") String email,
            @QueryParam("password") String password
    );

    /**
     *
     * @param login
     * @param newLogin
     * @return
     */
    @PUT
    @Path("updateLogin/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomerLogin(
            @PathParam("login") String login,
            @QueryParam("newLogin") String newLogin);

    @PUT
    @Path("updateFirstname/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomerFirstname(
            @PathParam("login") String login,
            @QueryParam("firstname") String firstname);

    @PUT
    @Path("updateLastname/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomerLastname(
            @PathParam("login") String login,
            @QueryParam("lastname") String lastname);

    @PUT
    @Path("updateEmail/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomerEmail(
            @PathParam("login") String login,
            @QueryParam("email") String email);

    @PUT
    @Path("updatePassword/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomerPassword(
            @PathParam("login") String login,
            @QueryParam("password") String password);

    @DELETE
    @Path("{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCustomer(
            @PathParam("login") String login);

}
