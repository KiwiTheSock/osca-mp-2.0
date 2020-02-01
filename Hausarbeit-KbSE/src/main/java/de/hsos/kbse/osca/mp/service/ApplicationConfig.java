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
public class ApplicationConfig extends Application {
    private Set<Class<?>>classes = new HashSet<>();

    /**
     *
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }


//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new java.util.HashSet<>();
//        addRestResourceClasses(resources);
//        return resources;
//    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(de.hsos.kbse.osca.mp.service.CustomerFacadeREST.class);
//        resources.add(de.hsos.kbse.osca.mp.service.DepartmentFacadeREST.class);
//        resources.add(de.hsos.kbse.osca.mp.service.ExamFacadeREST.class);
//        resources.add(de.hsos.kbse.osca.mp.service.TimeslotFacadeREST.class);
//    }
    
}
