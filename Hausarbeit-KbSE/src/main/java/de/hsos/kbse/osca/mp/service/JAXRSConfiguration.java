/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author nordm
 */
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
    private Set<Class<?>>classes = new HashSet<>();

    /**
     *
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> classes) {
        classes.add(de.hsos.kbse.osca.mp.service.CustomerFacadeREST.class);
        classes.add(de.hsos.kbse.osca.mp.service.DepartmentFacadeREST.class);
        classes.add(de.hsos.kbse.osca.mp.service.ExamFacadeREST.class);
    }
    
}
