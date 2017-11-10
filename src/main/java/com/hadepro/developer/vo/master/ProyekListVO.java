/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.vo.master;


import com.hadepro.developer.model.Proyek;
import java.util.List;

/**
 *
 * @author user
 */
public class ProyekListVO {
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

    public List<Proyek> getProyeks() {
        return proyeks;
    }

    public void setProyeks(List<Proyek> proyeks) {
        this.proyeks = proyeks;
    }
    private long totalProyeks;

    public long getTotalProyeks() {
        return totalProyeks;
    }

    public void setTotalProyeks(long totalProyeks) {
        this.totalProyeks = totalProyeks;
    }

    private String actionMessage;
    private String searchMessage;

    private List<Proyek> proyeks;

    public ProyekListVO() {
    }
 
}
