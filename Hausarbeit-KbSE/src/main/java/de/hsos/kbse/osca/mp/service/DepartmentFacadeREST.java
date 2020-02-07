/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import de.hsos.kbse.osca.mp.RESTfulInterfaces.DepartmentRestInterface;
import de.hsos.kbse.osca.mp.controller.DepartmentRepository;
import de.hsos.kbse.osca.mp.entity.Department;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
@Path("de.hsos.kbse.osca.mp.entity.department")
public class DepartmentFacadeREST implements DepartmentRestInterface {

    @Inject
    private DepartmentRepository repo;

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public DepartmentFacadeREST() {
//        super(Department.class);
    }

//    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Response createDepartment(String modulname, String semester) {
        Department dep = new Department(modulname, semester);
        try {
            this.repo.create(dep);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateDepartment(String modulname, String newModulname, String semester) {
        try {
            Department dep = this.repo.getByModulname(modulname);
            dep.setSemester(semester);
            dep.setModulename(newModulname);
            this.repo.edit(dep);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateDepartmentModulname(String modulname, String newModulname) {
        try {
            Department dep = this.repo.getByModulname(modulname);
            dep.setModulename(newModulname);
            this.repo.edit(dep);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response updateDepartmentSemester(String modulname, String semester) {
        try {
            Department dep = this.repo.getByModulname(modulname);
            dep.setSemester(semester);
            this.repo.edit(dep);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response findDepartment(String modulname) {
        try {
            Department dep = this.repo.getByModulname(modulname);
            this.repo.remove(dep);
            return Response
                    .status(Response.Status.GONE)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response deleteDepartment(String modulname) {
        try {
            Department dep = this.repo.getByModulname(modulname);
            this.repo.remove(dep);
            return Response
                    .status(Response.Status.GONE)
                    .entity(this.repo.getJsonb().toJson(dep)).build();
        } catch (NullPointerException | NotFoundException | IllegalArgumentException | NoResultException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Long id, Department entity) {
        this.repo.edit(entity);

    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.repo.remove(this.repo.find(id));
    }

    @GET
    @Path("{id}")
    public Department find(@PathParam("id") Long id) {
        return this.repo.find(id);
    }

    @GET
    public List<Department> findAll() {
        return this.repo.findAll();
    }

    @GET
    @Path("{from}/{to}")
    public List<Department> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return this.repo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(this.repo.count());
    }

}
