/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.vo;

import java.util.List;
import com.hadepro.developer.model.AksesMenu;

/**
 *
 * @author user
 */
public class AksesMenuListVO {
    private int pagesCount;

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public long getTotalAksesMenu() {
        return totalAksesMenu;
    }

    public void setTotalAksesMenu(long totalAksesMenu) {
        this.totalAksesMenu = totalAksesMenu;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }

    public List<AksesMenu> getAksesmenu() {
        return aksesmenu;
    }

    public void setAksesmenu(List<AksesMenu> aksesmenu) {
        this.aksesmenu = aksesmenu;
    }
    private long totalAksesMenu;

    private String actionMessage;
    private String searchMessage;

    private List<AksesMenu> aksesmenu;

    public AksesMenuListVO() {
    }
 
}
