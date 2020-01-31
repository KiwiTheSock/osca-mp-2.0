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
    
    public static void createSession(Long id) {
        
    }
    
}
