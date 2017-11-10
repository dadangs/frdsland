/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.vo.master;


 
import java.util.List;

import com.hadepro.developer.model.Konsumen;

/**
 *
 * @author user
 */
public class KonsumenListVO {
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

    public List<Konsumen> getKonsumens() {
        return konsumens;
    }

    public void setKonsumens(List<Konsumen> konsumens) {
        this.konsumens = konsumens;
    }
    private long totalKonsumens;

    public long getTotalKonsumens() {
        return totalKonsumens;
    }

    public void setTotalKonsumens(long totalKonsumens) {
        this.totalKonsumens = totalKonsumens;
    }

    private String actionMessage;
    private String searchMessage;

    private List<Konsumen> konsumens;

    public KonsumenListVO() {
    }
 
}
