/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.entity;

import de.hsos.kbse.osca.mp.abstracts.AbstractEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Philipp
 */
@Entity
@Table(name = "Department")
public class Department extends AbstractEntity {
    
    //ID wird in AbstractEntity erstellt
    @NotNull(message = "Modulename can't be empty")
    private String moduleName;
    @NotNull(message = "Semester can't be empty")
    private String semester;
    
    @ManyToMany
    private Set<Customer> users = new HashSet<>();;

    @OneToMany
    private Set<Exam> exam = new HashSet<>();;
    
    public Department() {
    }

    public Department(String moduleName, String semester) {
        this.moduleName = moduleName;
        this.semester = semester;
    }

    public Department(String moduleName, String semester, HashSet<Customer> users, HashSet<Exam> exam) {
        this.moduleName = moduleName;
        this.semester = semester;
        this.users = users;
        this.exam = exam;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set<Customer> getUsers() {
        return users;
    }

    public void setUsers(HashSet<Customer> users) {
        this.users = users;
    }

    public Set<Exam> getExam() {
        return exam;
    }

    public void setExam(HashSet<Exam> exam) {
        this.exam = exam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.moduleName);
        hash = 47 * hash + Objects.hashCode(this.semester);
        hash = 47 * hash + Objects.hashCode(this.users);
        hash = 47 * hash + Objects.hashCode(this.exam);
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
        if (this.semester != other.semester) {
            return false;
        }
        if (!Objects.equals(this.moduleName, other.moduleName)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        if (!Objects.equals(this.exam, other.exam)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fach{" + "moduleName=" + moduleName + ", semester=" + semester + ", users=" + users + ", exam=" + exam + '}';
    }


    
}
