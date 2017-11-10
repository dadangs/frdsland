/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.vo;

import java.util.List;
import com.hadepro.developer.model.Biller;

/**
 *
 * @author user
 */
public class BillerListVO {
    private int pagesCount;    
    private long totalBillers;

    private String actionMessage;
    private String searchMessage;

    private List<Biller> billers;

    public BillerListVO() {
    }

    public BillerListVO(int pages, long totalBillers, List<Biller> billers) {
        this.pagesCount = pages;
        this.billers = billers;
        this.totalBillers = totalBillers;
    }
    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public long getTotalBillers() {
        return totalBillers;
    }

    public void setTotalBillers(long totalBillers) {
        this.totalBillers = totalBillers;
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

    public List<Biller> getBillers() {
        return billers;
    }

    public void setBillers(List<Biller> billers) {
        this.billers = billers;
    }
}
