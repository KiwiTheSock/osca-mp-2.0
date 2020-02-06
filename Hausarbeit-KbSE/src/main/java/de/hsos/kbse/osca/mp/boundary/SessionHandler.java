/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.boundary;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PMarkmann
 */
public class SessionHandler {

    private static final Map<Long, HttpSession> logins = new HashMap<>();

    public static Map<Long, HttpSession> getLogins() {
        return logins;
    }
    
    public static void store(Long id, HttpSession session) {
        System.out.println("de.hsos.kbse.osca.mp.boundary.SessionHandler.store()\nID: " + id + " hinzugefügt!\n");
        logins.put(id, session);
    }

    public static void delete(Long id) {
        HttpSession session = logins.get(id);
        if (session != null) {
            logins.remove(id);
            session.invalidate();
        }
    }

    public static void remove(Long id) {
        HttpSession session = logins.get(id);
        if (session != null) {
            logins.remove(id);
        }
    }

}
