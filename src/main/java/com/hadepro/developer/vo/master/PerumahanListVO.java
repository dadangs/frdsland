/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.vo.master;


import com.hadepro.developer.model.Perumahan;

import java.util.List;

/**
 *
 * @author user
 */
public class PerumahanListVO {
   private int pagesCount;    

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
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

    public List<Perumahan> getPerumahans() {
        return perumahans;
    }

    public void setPerumahans(List<Perumahan> perumahans) {
        this.perumahans = perumahans;
    }
    private long totalPerumahans;

    public long getTotalPerumahans() {
        return totalPerumahans;
    }

    public void setTotalPerumahans(long totalPerumahans) {
        this.totalPerumahans = totalPerumahans;
    }

    private String actionMessage;
    private String searchMessage;

    private List<Perumahan> perumahans;

    public PerumahanListVO() {
    }
 
}
