/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

/**
 *
 * @author Julius
 */
public class SPCtl {
    DBHandler dbHandler;
    MainPage mainPage;

    public SPCtl(MainPage mainPage) {
        this.addMainPage(mainPage);
    }

    public void addDBHanlder(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

}
