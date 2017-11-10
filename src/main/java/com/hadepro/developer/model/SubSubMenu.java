/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.model;

/**
 *
 * @author user
 */
public class SubSubMenu {
   private String idMenu;
   private String parentIdMenu;

   public String getParentIdMenu() {
        return parentIdMenu;
    }

    public void setParentIdMenu(String parentIdMenu) {
        this.parentIdMenu = parentIdMenu;
    }
    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getCaptionMenu() {
        return captionMenu;
    }

    public void setCaptionMenu(String captionMenu) {
        this.captionMenu = captionMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public String getIconMenu() {
        return iconMenu;
    }

    public void setIconMenu(String iconMenu) {
        this.iconMenu = iconMenu;
    }

    public Integer getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Integer hasChildren) {
        this.hasChildren = hasChildren;
    }
   private String captionMenu;
   private String urlMenu;
   private String iconMenu;
   private Integer hasChildren;
   
}
