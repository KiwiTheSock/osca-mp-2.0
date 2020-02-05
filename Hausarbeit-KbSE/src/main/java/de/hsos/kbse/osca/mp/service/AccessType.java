/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.osca.mp.service;

/**
 *
 * @author nordm
 */
public enum AccessType {
    ADMINISTRATOR (0),
    DOZENT (1),
    STUDENT (2);
    
    private final int levelCode;

    private AccessType(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return levelCode;
    }
    
}
