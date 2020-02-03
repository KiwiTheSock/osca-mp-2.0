/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.view;

import de.hsos.kbse.osca.mp.abstracts.AbstractRepoAccesor;
import de.hsos.kbse.osca.mp.entity.Department;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import de.hsos.kbse.osca.mp.logger.interceptorbinding.LevelEnum;
import de.hsos.kbse.osca.mp.logger.interceptorbinding.Logable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author wihowert
 */
@Named
@ViewScoped
public class DropdownViewDozent extends AbstractRepoAccesor implements Serializable {

    // Listen zum Anlegen des Moduls
    private Map<String, String> terms = new HashMap<>();
    private Map<String, Integer> durations = new HashMap<>();
    private Map<String, Integer> studentCounts = new HashMap<>();
    private Map<String, String> examMins = new HashMap<>();
    private Map<String, String> examMaxs = new HashMap<>();
    // Listen zum Konvertieren und Anzeigen
    private List<String> convertListTerms = new ArrayList<>();
    private List<String> convertListDurations = new ArrayList<>();
    private List<String> convertListStudentCounts = new ArrayList<>();
    private List<String> convertListExamMins = new ArrayList<>();
    private List<String> convertListExamMaxs = new ArrayList<>();
    // Variablen zum Anzeigen der aktuellen Werte
    private String term;
    private String text;
    private String modulName;
    private String examMin = "10:00";
    private String examMax = "16:00";
    private int duration = 30;
    private int studentCount = 1;
    // Booleans zum Ueberpruefen
    private boolean termExists = false;
    private boolean buttonCheck = false;
    private boolean buttonCheck2 = false;
    private boolean checkIsStudent = false;

    //Dropdown Kalender
        // Listen zum Verwalten des Kalenders
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private Map<Integer, Date> days = new HashMap<>();
    private List<Date> convertListDays = new ArrayList<>();
        // Variablen fuer den Kalender
    private Date date2;
    private Date minDate;
    private Date maxDate;
    private Date minTime;
    private Date maxTime;
    private Date minDateTime;
    private Date maxDateTime;
    private Date dateDe;
    private Date dateTimeDe;
    private Date day;
    private static int key = 0;

    // Beispielhafte Initialisierung der Daten
    @PostConstruct
    public void init() {
        setInvalidDates(new ArrayList<>());
        Date today = new Date();
        getInvalidDates().add(today);
        long oneDay = 24 * 60 * 60 * 1000;
        for (int i = 0; i < 5; i++) {
            getInvalidDates().add(new Date(getInvalidDates().get(i).getTime() + oneDay));
        }

        setInvalidDays(new ArrayList<>());
        getInvalidDays().add(0);
        /* the first day of week is disabled */
        getInvalidDays().add(3);

        setMinDate(new Date(today.getTime() - (365 * oneDay)));
        setMaxDate(new Date(today.getTime() + (365 * oneDay)));

        Calendar tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 9);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        setMinTime(tmp.getTime());

        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 17);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        setMaxTime(tmp.getTime());

        setMinDateTime(new Date(today.getTime() - (7 * oneDay)));
        setMaxDateTime(new Date(today.getTime() + (7 * oneDay)));

        setDateDe(GregorianCalendar.getInstance().getTime());
        setDateTimeDe(GregorianCalendar.getInstance().getTime());

        // 
        getTerms().put("Semester1", "WiSe 17/18");
        getTerms().put("Semester2", "SoSe 18");
        getTerms().put("Semester3", "WiSe 18/19");
        getTerms().put("Semester4", "SoSe 19");
        getTerms().put("Semester5", "WiSe 19/20");
        getTerms().put("Semester6", "SoSe 20");

        getDurations().put("Duration1", 20);
        getDurations().put("Duration2", 30);

        getStudentCounts().put("1", 1);
        getStudentCounts().put("2", 2);
        getStudentCounts().put("3", 3);
        getStudentCounts().put("4", 4);
        getStudentCounts().put("5", 5);

        getExamMins().put("10:00", "10:00");
        getExamMins().put("11:00", "11:00");
        getExamMins().put("12:00", "12:00");

        getExamMaxs().put("16:00", "16:00");
        getExamMaxs().put("17:00", "17:00");
        getExamMaxs().put("18:00", "18:00");

        for (Map.Entry<String, String> map : getTerms().entrySet()) {
            getConvertListTerms().add(map.getValue());
        }

        for (Map.Entry<String, String> map : getExamMins().entrySet()) {
            getConvertListExamMins().add(map.getValue());
        }
        for (Map.Entry<String, String> map : getExamMaxs().entrySet()) {
            getConvertListExamMaxs().add(map.getValue());
        }

        getConvertListTerms().sort(Comparator.naturalOrder());

        for (Map.Entry<String, Integer> map : getDurations().entrySet()) {
            getConvertListDurations().add(map.getValue().toString());
        }

        for (Map.Entry<String, Integer> map : getStudentCounts().entrySet()) {
            getConvertListStudentCounts().add(map.getValue().toString());
        }
    }

    // Nachricht fuer den Tag des Kalenders
    public void displayLogDate() {
        FacesMessage msg;
        if (getDate2() != null) {

            msg = new FacesMessage("Bestaetigt: ", "Tag " + getDate2() + " hinzugefuegt!");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Tag konnte nicht gesetzt werden.");
        }
        System.out.println("Hier wird EXAM angelegt!\nTag: "+getDate2()+"\nDuration:"+this.duration+"\nStarzeit: " + getExamMin()+"\nEndzeit: "+getExamMax()+"\nAmount of Space: "+this.studentCount+"\nFür Module: "+this.modulName);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Nachricht fuer das Anlegen eines Moduls
    public void displayLog() {
        FacesMessage msg;
        if (getTerm() != null) {
            msg = new FacesMessage("Bestaetigt: ", "Fuer das " + getTerm() + " das Modul \"" + getModulName()
                    + "\" mit der Pruefungsdauer " + getDuration() + " fuer "
                    + getStudentCount() + " Pruefling(e) angelegt");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Parameter nicht ausgewaehlt.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @void redirect to Student/Dozent
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void redirect(String redir) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (isCheckIsStudent() == true) {
            context.redirect(redir);
        } else {
            context.redirect(redir);
        }
    }

    /**
     * @return the terms
     */
    public Map<String, String> getTerms() {
        return terms;
    }

    /**
     * @param terms the terms to set
     */
    public void setTerms(Map<String, String> terms) {
        this.terms = terms;
    }

    /**
     * @return the term
     */
    @Logable(logLevel = LevelEnum.INFO)
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void setTerm(String term) {
        System.out.println("Term setted: " + term);
        if (term != null) {
            this.setTermExists(true);
        } else {
            this.setTermExists(false);
        }
        this.term = term;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the termExists
     */
    public boolean isTermExists() {
        return termExists;
    }

    /**
     * @param termExists the termExists to set
     */
    public void setTermExists(boolean termExists) {
        this.termExists = termExists;
    }

    /**
     * @return the convertListTerms
     */
    public List<String> getConvertListTerms() {
        return convertListTerms;
    }

    /**
     * @param convertListTerms the convertListTerms to set
     */
    public void setConvertListTerms(List<String> convertListTerms) {
        this.convertListTerms = convertListTerms;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the studentCount
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * @param studentCount the studentCount to set
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    /**
     * @return the durations
     */
    public Map<String, Integer> getDurations() {
        return durations;
    }

    /**
     * @param durations the durations to set
     */
    public void setDurations(Map<String, Integer> durations) {
        this.durations = durations;
    }

    /**
     * @return the convertListDurations
     */
    public List<String> getConvertListDurations() {
        return convertListDurations;
    }

    /**
     * @param convertListDurations the convertListDurations to set
     */
    public void setConvertListDurations(List<String> convertListDurations) {
        this.convertListDurations = convertListDurations;
    }

    /**
     * @return the studentCounts
     */
    public Map<String, Integer> getStudentCounts() {
        return studentCounts;
    }

    /**
     * @param studentCounts the studentCounts to set
     */
    public void setStudentCounts(Map<String, Integer> studentCounts) {
        this.studentCounts = studentCounts;
    }

    /**
     * @return the convertListStudentCounts
     */
    public List<String> getConvertListStudentCounts() {
        return convertListStudentCounts;
    }

    /**
     * @param convertListStudentCounts the convertListStudentCounts to set
     */
    public void setConvertListStudentCounts(List<String> convertListStudentCounts) {
        this.convertListStudentCounts = convertListStudentCounts;
    }

    /**
     * @return the buttonCheck
     */
    public boolean isButtonCheck() {
        return buttonCheck;
    }

    /**
     * @param buttonCheck the buttonCheck to set
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void setButtonCheck(boolean buttonCheck) {
        System.out.println("CHECK");
        this.buttonCheck = buttonCheck;
        this.Departments.add(new Department(this.term,"Semester X"));
    }

    /**
     * @return the modulName
     */
    public String getModulName() {
        return modulName;
    }

    /**
     * @param modulName the modulName to set
     */
    @Logable(logLevel = LevelEnum.INFO)
    public void setModulName(String modulName) {
        System.out.println("Modulname gesetzt: " + modulName);
        this.modulName = modulName;
    }

    /**
     * @return the examMins
     */
    public Map<String, String> getExamMins() {
        return examMins;
    }

    /**
     * @param examMins the examMins to set
     */
    public void setExamMins(Map<String, String> examMins) {
        this.examMins = examMins;
    }

    /**
     * @return the examMaxs
     */
    public Map<String, String> getExamMaxs() {
        return examMaxs;
    }

    /**
     * @param examMaxs the examMaxs to set
     */
    public void setExamMaxs(Map<String, String> examMaxs) {
        this.examMaxs = examMaxs;
    }

    /**
     * @return the convertListExamMins
     */
    public List<String> getConvertListExamMins() {
        return convertListExamMins;
    }

    /**
     * @param convertListExamMins the convertListExamMins to set
     */
    public void setConvertListExamMins(List<String> convertListExamMins) {
        this.convertListExamMins = convertListExamMins;
    }

    /**
     * @return the convertListExamMaxs
     */
    public List<String> getConvertListExamMaxs() {
        return convertListExamMaxs;
    }

    /**
     * @param convertListExamMaxs the convertListExamMaxs to set
     */
    public void setConvertListExamMaxs(List<String> convertListExamMaxs) {
        this.convertListExamMaxs = convertListExamMaxs;
    }

    /**
     * @return the examMin
     */
    public String getExamMin() {
        return examMin;
    }

    /**
     * @param examMin the examMin to set
     */
    public void setExamMin(String examMin) {
        this.examMin = examMin;
    }

    /**
     * @return the examMax
     */
    public String getExamMax() {
        return examMax;
    }

    /**
     * @param examMax the examMax to set
     */
    public void setExamMax(String examMax) {
        this.examMax = examMax;
    }

    /**
     * @return the buttonCheck2
     */
    public boolean isButtonCheck2() {
        return buttonCheck2;
    }

    /**
     * @param buttonCheck2 the buttonCheck2 to set
     */
    public void setButtonCheck2(boolean buttonCheck2) {
        this.buttonCheck2 = buttonCheck2;
    }

    /**
     * @return the checkIsStudent
     */
    public boolean isCheckIsStudent() {
        return checkIsStudent;
    }

    /**
     * @param checkIsStudent the checkIsStudent to set
     */
    public void setCheckIsStudent(boolean checkIsStudent) {
        this.checkIsStudent = checkIsStudent;
    }

    /**
     * @return the date2
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
        System.out.println("Date ....." + this.date2);

        getDays().put(this.key, date2);
        this.key += this.key + 1;

        for (Map.Entry<Integer, Date> map : getDays().entrySet()) {
            getConvertListDays().add(map.getValue());
            System.out.println("List: " + map.getValue().toString());
        }
    }

    /**
     * @return the invalidDates
     */
    public List<Date> getInvalidDates() {
        return invalidDates;
    }

    /**
     * @param invalidDates the invalidDates to set
     */
    public void setInvalidDates(List<Date> invalidDates) {
        this.invalidDates = invalidDates;
    }

    /**
     * @return the invalidDays
     */
    public List<Integer> getInvalidDays() {
        return invalidDays;
    }

    /**
     * @param invalidDays the invalidDays to set
     */
    public void setInvalidDays(List<Integer> invalidDays) {
        this.invalidDays = invalidDays;
    }

    /**
     * @return the minDate
     */
    public Date getMinDate() {
        return minDate;
    }

    /**
     * @param minDate the minDate to set
     */
    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    /**
     * @return the maxDate
     */
    public Date getMaxDate() {
        return maxDate;
    }

    /**
     * @param maxDate the maxDate to set
     */
    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * @return the minTime
     */
    public Date getMinTime() {
        return minTime;
    }

    /**
     * @param minTime the minTime to set
     */
    public void setMinTime(Date minTime) {
        this.minTime = minTime;
    }

    /**
     * @return the maxTime
     */
    public Date getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the minDateTime
     */
    public Date getMinDateTime() {
        return minDateTime;
    }

    /**
     * @param minDateTime the minDateTime to set
     */
    public void setMinDateTime(Date minDateTime) {
        this.minDateTime = minDateTime;
    }

    /**
     * @return the maxDateTime
     */
    public Date getMaxDateTime() {
        return maxDateTime;
    }

    /**
     * @param maxDateTime the maxDateTime to set
     */
    public void setMaxDateTime(Date maxDateTime) {
        this.maxDateTime = maxDateTime;
    }

    /**
     * @return the dateDe
     */
    public Date getDateDe() {
        return dateDe;
    }

    /**
     * @param dateDe the dateDe to set
     */
    public void setDateDe(Date dateDe) {
        this.dateDe = dateDe;
    }

    /**
     * @return the dateTimeDe
     */
    public Date getDateTimeDe() {
        return dateTimeDe;
    }

    /**
     * @param dateTimeDe the dateTimeDe to set
     */
    public void setDateTimeDe(Date dateTimeDe) {
        this.dateTimeDe = dateTimeDe;
    }

    /**
     * @return the days
     */
    public Map<Integer, Date> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(Map<Integer, Date> days) {
        this.days = days;
    }

    /**
     * @return the convertListDays
     */
    public List<Date> getConvertListDays() {
        return convertListDays;
    }

    /**
     * @param convertListDays the convertListDays to set
     */
    public void setConvertListDays(List<Date> convertListDays) {
        this.convertListDays = convertListDays;
    }

    /**
     * @return the day
     */
    public Date getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(Date day) {
        this.day = day;
    }

    /**
     * @return the key
     */
    public static int getKey() {
        return key;
    }

    /**
     * @param aKey the key to set
     */
    public static void setKey(int aKey) {
        key = aKey;
    }

}
