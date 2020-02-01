/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.oscar.mp.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import logger.interceptorbinding.LevelEnum;
import logger.interceptorbinding.Logable;

/**
 *
 * @author wihowert
 */
@Named
@SessionScoped
public class CalendarViewDozent implements Serializable {

    private Date date2;
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private Date minDate;
    private Date maxDate;
    private Date minTime;
    private Date maxTime;
    private Date minDateTime;
    private Date maxDateTime;
    private Date dateDe;
    private Date dateTimeDe;
    private Map<Integer, Date> days = new HashMap<>();
    private List<Date> convertListDays = new ArrayList<>();
    private Date day;
    private static int key = 0;

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
    }

    public void displayLogDate() {
        FacesMessage msg;
        if (getDate2() != null) {
            msg = new FacesMessage("Bestätigt: ", "Tag " + getDate2() + " hinzugefügt");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Tag konnte nicht gesetzt werden.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
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
    @Logable(logLevel = LevelEnum.INFO)
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
}
