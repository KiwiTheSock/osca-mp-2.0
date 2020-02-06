/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.view;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Department;
import de.hsos.kbse.osca.mp.entity.Exam;
import de.hsos.kbse.osca.mp.logger.interceptorbinding.LevelEnum;
import de.hsos.kbse.osca.mp.logger.interceptorbinding.Logable;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author wihowert
 */
@Named
@SessionScoped
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
    public void init() {

        setModuls(new HashMap<>());
        fillDepartments();
    }

    public void fillDepartments() {
        System.out.println("de.hsos.kbse.osca.mp.view.DropdownViewStudent.fillDepartments()");
        //Get all departmens
        List<Department> tmp = this.Departments.getAll();
        tmp.forEach((_item) -> {
            getModuls().put(_item.getModulename(), _item.getModulename());
        });
    }

    @Logable(logLevel = LevelEnum.INFO)
    public void fillDays() {
        System.out.println("GANZ GENAU");
        System.out.println("de.hsos.kbse.osca.mp.view.DropdownViewStudent.fillDays()");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        setDays(new HashMap<>());
        List<Exam> tmp = this.Exams.getAllDaybyDepartment(Departments.getByModulname(getModul()).getId());
        tmp.forEach((_item) -> {
            //System.out.println(_item.toString());
            getDays().put(dateFormat.format(_item.getDatum()), dateFormat.format(_item.getDatum()));
        });
        System.out.println("GetDays:"); // Funktioniert
        System.out.println(getDays().toString());
    }

    @Logable(logLevel = LevelEnum.INFO)
    public void testMe() {
        System.out.println("DAS WARN ERFOLG UND ICH BIN DAS MODUL: " + getModul());
    }

    @Logable(logLevel = LevelEnum.INFO)
    public void fillTimes() throws ParseException {
        setTimes(new HashMap<>());
        List<Exam> tmp = this.Exams.findByDay(this.day);

        //Ausgabe
        System.out.println(tmp.toString());
        System.out.println("GetDay(): " + this.getDay());

        tmp.forEach((_item) -> {
            getTimes().put(_item.getBeginn().toString(), _item.getFinish().toString());
        });
        
        System.out.println(getTimes().toString());
    }

    /**
     * onDepartmentErase
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void onDepartmentErase() {
        System.out.println("Alles klar, weiter gehts ...");
        FacesMessage msg;
        if (getModul() != null && getDay() != null && getTime() != null) {
            msg = new FacesMessage("Bestaetigt: ", getModul() + " am " + getDay() + " um " + getTime() + " Uhr freigegeben.");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "entsprechende Parameter nicht ausgewaehlt ...");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * onDepartmentChange
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void onDepartmentChange() {
        System.out.println("Alles klar, weiter gehts ...");
        FacesMessage msg;
        if (getModul() != null && getDay() != null && getTime() != null) {
            msg = new FacesMessage("Bestaetigt: ", getModul() + " am " + getDay() + " um " + getTime() + " Uhr bestaetigt.");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "entsprechende Parameter nicht ausgewaehlt ...");
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
