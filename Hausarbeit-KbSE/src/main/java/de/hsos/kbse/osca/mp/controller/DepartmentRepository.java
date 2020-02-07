/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.controller;

import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.abstracts.AbstractFacade;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp
 */
@Named
@RequestScoped
public class DepartmentRepository extends AbstractFacade<Department> {

    @Inject
    private Jsonb jsonb;

    public DepartmentRepository() {
        super(Department.class);
    }

    @PersistenceContext(unitName = "de.hsos.kbse.oscar.mp_Hausarbeit-KbSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Department getByModulname(String modulename) {
        TypedQuery<Department> query;
        query = this.getEntityManager().createNamedQuery("Department.findByModulename", Department.class);
        return query.setParameter("modulename", modulename).getSingleResult();
    }

    public List<Department> getAll() {
        TypedQuery<Department> query;
        query = this.em.createNamedQuery("Department.findAll", Department.class);
        return query.getResultList();
    }


    public void setJsonb(Jsonb jsonb) {
        this.jsonb = jsonb;
    }

    public Jsonb getJsonb() {
        return jsonb;
    }
}
