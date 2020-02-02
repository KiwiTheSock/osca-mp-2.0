/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.view;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.entity.Exam;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author wihowert
 */
@RequestScoped
public class DropdownViewStudent extends AbstractRepoAccesor implements Serializable {

    // Listen fuer angemeldete Module
    private Map<String, Map<String, String>> modulDay = new HashMap<>();
    private Map<String, Map<String, String>> dayTime = new HashMap<>();
    private Map<String, String> moduls = new HashMap<>();
    private Map<String, String> days = new HashMap<>();
    private Map<String, String> students = new HashMap<>();
    private Map<String, String> times = new HashMap<>();
    
    // Variablen zum Anzeigen
    private String student;
    private String modul;
    private String day;
    private String time;

    // Beispielhafte Initialisierung von Daten
    @PostConstruct
    public void init() {
        
        //Load all Departments
        //fillModules();
        
        setModuls(new HashMap<>());
        setTimes(new HashMap<>());
        setStudents(new HashMap<>());

        getStudents().put("Stud Test", "Stud Test");
        getStudents().put("Stud TestDos", "Stud TestDos");
        getStudents().put("Stud TestTres", "Stud TestTres");

        /*        getModuls().put("Mathe1", "Mathe 1");
        getModuls().put("KBSE", "KBSE");
        getModuls().put("OOAD", "OOAD");*/

        getTimes().put("13:00", "13:00");
        getTimes().put("14:00", "14:00");
        getTimes().put("15:00", "15:00");
        //(K,V)
        getDays().put("15.10.2019", "15.10.2019");
        getDays().put("16.10.2019", "16.10.2019");
        getDays().put("17.10.2019", "17.10.2019");
        getModulDay().put("Mathe 1", getDays());

        getDays().put("18.10.2019", "18.10.2019");
        getDays().put("19.10.2019", "19.10.2019");
        getDays().put("20.10.2019", "20.10.2019");
        getModulDay().put("KBSE", getDays());

        getDays().put("15.10.2019", "15.10.2019");
        getDays().put("16.10.2019", "16.10.2019");
        getDays().put("17.10.2019", "17.10.2019");
        getModulDay().put("OOAD", getDays());

        getDayTime().put("15.10.2019", getTimes());
    }
    
    public void fillModules() {
        List<Department> tmp = this.Departments.getAll();
        tmp.forEach((_item) -> {
            getModuls().put(_item.getSemester(), _item.getModulename());
        });
    }
    
    public void fillDays() {
        List<Exam> tmp = this.Exams.getAllDaybyDepartment();
    }

    // Nachricht fuer Bestaetigung/Freigeben des Termins
    public void displayLog() {
        FacesMessage msg;
        if (getDay() != null && getModul() != null && getTime() != null) {
            msg = new FacesMessage("Bestaetigt: ", getModul() + " am " + getDay() + " um " + getTime() + " Uhr bestaetigt.");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Parameter nicht ausgewaehlt.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @param modulDay the modulDay to set
     */
    public void setModulDay(Map<String, Map<String, String>> modulDay) {
        this.modulDay = modulDay;
    }

    /**
     * @param moduls the moduls to set
     */
    public void setModuls(Map<String, String> moduls) {
        this.moduls = moduls;
    }

    /**
     * @param days the days to set
     */
    public void setDays(Map<String, String> days) {
        this.days = days;
    }

    /**
     * @return the times
     */
    public Map<String, String> getTimes() {
        return times;
    }

    /**
     * @param times the times to set
     */
    public void setTimes(Map<String, String> times) {
        this.times = times;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the dayTime
     */
    public Map<String, Map<String, String>> getDayTime() {
        return dayTime;
    }

    /**
     * @param dayTime the dayTime to set
     */
    public void setDayTime(Map<String, Map<String, String>> dayTime) {
        this.dayTime = dayTime;
    }

    /**
     * @return the student
     */
    public String getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(String student) {
        this.student = student;
    }

    /**
     * @return the students
     */
    public Map<String, String> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Map<String, String> students) {
        this.students = students;
    }

    /**
     * @return the modulDay
     */
    public Map<String, Map<String, String>> getModulDay() {
        return modulDay;
    }

    /**
     * @return the moduls
     */
    public Map<String, String> getModuls() {
        return moduls;
    }

    /**
     * @return the days
     */
    public Map<String, String> getDays() {
        return days;
    }

    /**
     * @return the modul
     */
    public String getModul() {
        return modul;
    }

    /**
     * @param modul the modul to set
     */
    public void setModul(String modul) {
        this.modul = modul;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

}
