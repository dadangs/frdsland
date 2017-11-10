package com.hadepro.developer.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

 
public class Pembayaran {
 @JsonInclude(JsonInclude.Include.NON_NULL) 
 private int id;
 
 public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private int idtransaksi;
public int getIdtransaksi() {
	return idtransaksi;
}
public void setIdtransaksi(int idtransaksi) {
	this.idtransaksi = idtransaksi;
}
private String noktp;
 private int angsuranke;
 private int id_produk;
 private String tanggal_bayar;
 private BigDecimal jumlah_bayar;
 private Transaksi transaksi;
  
public Transaksi getTransaksi() {
	return transaksi;
}
public void setTransaksi(Transaksi transaksi) {
	this.transaksi = transaksi;
}
public BigDecimal getJumlah_bayar() {
	return jumlah_bayar;
}
public void setJumlah_bayar(BigDecimal jumlah_bayar) {
	this.jumlah_bayar = jumlah_bayar;
}
public BigDecimal getSisa() {
	return sisa;
}
public void setSisa(BigDecimal sisa) {
	this.sisa = sisa;
}
private BigDecimal sisa;
public String getNoktp() {
	return noktp;
}
public void setNoktp(String noktp) {
	this.noktp = noktp;
}
public int getAngsuranke() {
	return angsuranke;
}
public void setAngsuranke(int angsuranke) {
	this.angsuranke = angsuranke;
}
public int getId_produk() {
	return id_produk;
}
public void setId_produk(int id_produk) {
	this.id_produk = id_produk;
}
public String getTanggal_bayar() {
	return tanggal_bayar;
}
public void setTanggal_bayar(String tanggal_bayar) {
	this.tanggal_bayar = tanggal_bayar;
}
   
}
