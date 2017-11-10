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
public class Perumahan {
    private String id_proyek;
    public String getId_proyek() {
		return id_proyek;
	}
	public void setId_proyek(String id_proyek) {
		this.id_proyek = id_proyek;
	}
	public String getId_perumahan() {
		return id_perumahan;
	}
	public void setId_perumahan(String id_perumahan) {
		this.id_perumahan = id_perumahan;
	}
	public String getNama_perumahan() {
		return nama_perumahan;
	}
	public void setNama_perumahan(String nama_perumahan) {
		this.nama_perumahan = nama_perumahan;
	}
	public String getTipe_perumahan() {
		return tipe_perumahan;
	}
	public void setTipe_perumahan(String tipe_perumahan) {
		this.tipe_perumahan = tipe_perumahan;
	}
	public Integer getJumlah_unit() {
		return jumlah_unit;
	}
	public void setJumlah_unit(Integer jumlah_unit) {
		this.jumlah_unit = jumlah_unit;
	}
	private String id_perumahan;
    private String nama_perumahan;
    private String tipe_perumahan;
    private Integer jumlah_unit;
    

}
