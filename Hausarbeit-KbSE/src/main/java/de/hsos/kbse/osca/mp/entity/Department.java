/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nordm
 */
@Entity
@Table(name = "DEPARTMENT")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByModulename", query = "SELECT d FROM Department d WHERE d.modulename = :modulename"),
    @NamedQuery(name = "Department.findBySemester", query = "SELECT d FROM Department d WHERE d.semester = :semester")})
public class Department extends AbstractEntity {

    private String modulename;
    private String semester;

//    @OneToMany(cascade = {CascadeType.ALL})
//    private Set<Customer> customerSet;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Exam> examSet;

    public Department() {
    }

    public Department(String modulename, String semester) {
        this.modulename = modulename;
        this.semester = semester;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set<Exam> getExamSet() {
        return examSet;
    }

    public void setExamSet(Set<Exam> examSet) {
        this.examSet = examSet;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.modulename);
        hash = 19 * hash + Objects.hashCode(this.semester);
        hash = 19 * hash + Objects.hashCode(this.examSet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.modulename, other.modulename)) {
            return false;
        }
        if (!Objects.equals(this.semester, other.semester)) {
            return false;
        }
        if (!Objects.equals(this.examSet, other.examSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Department{" + "modulename=" + modulename + ", semester=" + semester + ", examSet=" + examSet + '}';
    }
}