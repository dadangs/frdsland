package com.hadepro.developer.model;

public class Produk {
  private int id_developer;
  private int id_proyek;
  private int id_produk;
  private int is_jual;
  public int getIs_jual() {
	return is_jual;
}
public void setIs_jual(int is_jual) {
	this.is_jual = is_jual;
}

private String id_kategori;

//Developer developer;Proyek proyek;
  private String developer;
  public String getDeveloper() {
	return developer;
}
public void setDeveloper(String developer) {
	this.developer = developer;
}
public String getProyek() {
	return proyek;
}
public void setProyek(String proyek) {
	this.proyek = proyek;
}

private String proyek;
public String getId_kategori() {
	return id_kategori;
}
public void setId_kategori(String id_kategori) {
	this.id_kategori = id_kategori;
}

private String tipe_rumah;
  private String blok;
  private String luas_tanah;
  private String nounit;
  
   
public String getBlok() {
	return blok;
}
public void setBlok(String blok) {
	this.blok = blok;
}
public String getNounit() {
	return nounit;
}
public void setNounit(String nounit) {
	this.nounit = nounit;
}
 

  public int getId_developer() {
	return id_developer;
}
public void setId_developer(int id_developer) {
	this.id_developer = id_developer;
}
public int getId_proyek() {
	return id_proyek;
}
public void setId_proyek(int id_proyek) {
	this.id_proyek = id_proyek;
}
public int getId_produk() {
	return id_produk;
}
public void setId_produk(int id_produk) {
	this.id_produk = id_produk;
}

public String getTipe_rumah() {
	return tipe_rumah;
}
public void setTipe_rumah(String tipe_rumah) {
	this.tipe_rumah = tipe_rumah;
}
public String getLuas_tanah() {
	return luas_tanah;
}
public void setLuas_tanah(String luas_tanah) {
	this.luas_tanah = luas_tanah;
}
/*public Developer getDeveloper() {
	return developer;
}
public void setDeveloper(Developer developer) {
	this.developer = developer;
}
public Proyek getProyek() {
	return proyek;
}
public void setProyek(Proyek proyek) {
	this.proyek = proyek;
}*/

  public  Produk(){
	  
  }
}
