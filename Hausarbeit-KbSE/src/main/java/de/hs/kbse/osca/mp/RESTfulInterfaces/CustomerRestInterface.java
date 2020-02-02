/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hs.kbse.osca.mp.RESTfulInterfaces;

import de.hsos.kbse.osca.mp.entity.Customer;
import de.hsos.kbse.osca.mp.service.AccessType;
import java.util.List;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nordm
 */
public interface CustomerRestInterface {
    
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createStudent(@QueryParam("firstname") String firstname,
            @QueryParam("email") String email);

    @GET
    @Path("dozents")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAllDozents();

    @GET
    @Path("students")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findAllStudents();


}
