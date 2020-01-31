/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.abstracts;

import de.hsos.kbse.osca.mp.controller.CustomerRepository;
import de.hsos.kbse.osca.mp.controller.DepartmentRepository;
import javax.inject.Inject;

/**
 *
 * @author Philipp Markmann
 */
public abstract class AbstractRepoAccesor {
    
    @Inject
    protected CustomerRepository Customers;
    @Inject
    protected DepartmentRepository Departments;
    @Inject
    protected DepartmentRepository Exams;
    @Inject
    protected DepartmentRepository TimeSlots;
}
