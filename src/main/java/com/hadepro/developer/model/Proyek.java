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
public class Proyek {
	Developer developer;
    public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	private Integer id_developer;
	public Integer getId_developer() {
		return id_developer;
	}

	public void setId_developer(Integer id_developer) {
		this.id_developer = id_developer;
	}
	private Integer id_proyek;    
    private String nama_proyek;
    private String lokasi_proyek;
    private String luas;

    public Integer getId_proyek() {
        return id_proyek;
    }

    public void setId_proyek(Integer id_proyek) {
        this.id_proyek = id_proyek;
    }

    public String getNama_proyek() {
        return nama_proyek;
    }

    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }

    public String getLokasi_proyek() {
        return lokasi_proyek;
    }

    public void setLokasi_proyek(String lokasi_proyek) {
        this.lokasi_proyek = lokasi_proyek;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }
    
    public Proyek() {
    }

}
