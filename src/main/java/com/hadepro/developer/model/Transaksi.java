package com.hadepro.developer.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;


public class Transaksi {
	 @JsonInclude(JsonInclude.Include.NON_NULL) 
	private String noktp;
	private String nama;
	private String alamat;
	private String nama_rumah;
	private String tipe_rumah;
	private String noppjb;
	private BigDecimal dp;
	private int idtransaksi;
	 public int getIdtransaksi() {
		return idtransaksi;
	}
	public void setIdtransaksi(int idtransaksi) {
		this.idtransaksi = idtransaksi;
	}
	private int model_bayar;
	private int tenor;
	 private int model_bayardp;
	public int getModel_bayardp() {
		return model_bayardp;
	}
	public void setModel_bayardp(int model_bayardp) {
		this.model_bayardp = model_bayardp;
	}
	public int getTenordp() {
		return tenordp;
	}
	public void setTenordp(int tenordp) {
		this.tenordp = tenordp;
	}
	private int tenordp;

	private int id_produk;
	
	private String kategori;
	
	private String blok;
	public int getId_produk() {
		return id_produk;
	}
	public void setId_produk(int id_produk) {
		this.id_produk = id_produk;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
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
	private String nounit;
	private String luas_tanah;
	private BigDecimal harga_permeter;
	private BigDecimal harga_tanah;
	private String penambahan_lain;
	private BigDecimal harga_lain;
	private BigDecimal harga_kesepakatan;
	private BigDecimal total_penambahan;
	private BigDecimal totalHargaPlusPenambahan;
	private BigDecimal harga_total;
	
	private BigDecimal harga_appraisal;
	private BigDecimal cicilan_perbulan;
	private BigDecimal cicilan_perbulandp;
	public BigDecimal getCicilan_perbulandp() {
		return cicilan_perbulandp;
	}
	public void setCicilan_perbulandp(BigDecimal cicilan_perbulandp) {
		this.cicilan_perbulandp = cicilan_perbulandp;
	}
	private int tgl_jatuhtempo; 
	
	
	
	public String getNoktp() {
		return noktp;
	}
	public void setNoktp(String noktp) {
		this.noktp = noktp;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
 
	public String getNama_rumah() {
		return nama_rumah;
	}
	public void setNama_rumah(String nama_rumah) {
		this.nama_rumah = nama_rumah;
	}
	public String getTipe_rumah() {
		return tipe_rumah;
	}
	public void setTipe_rumah(String tipe_rumah) {
		this.tipe_rumah = tipe_rumah;
	}
	public String getNoppjb() {
		return noppjb;
	}
	public void setNoppjb(String noppjb) {
		this.noppjb = noppjb;
	}
	public BigDecimal getDp() {
		return dp;
	}
	public void setDp(BigDecimal dp) {
		this.dp = dp;
	}
 
	public BigDecimal getTotalHargaPlusPenambahan() {
		return totalHargaPlusPenambahan;
	}
	public void setTotalHargaPlusPenambahan(BigDecimal totalHargaPlusPenambahan) {
		this.totalHargaPlusPenambahan = totalHargaPlusPenambahan;
	}

	
	public BigDecimal getHarga_kesepakatan() {
		return harga_kesepakatan;
	}
	public void setHarga_kesepakatan(BigDecimal harga_kesepakatan) {
		this.harga_kesepakatan = harga_kesepakatan;
	}
	 

	public BigDecimal getHarga_total() {
		return harga_total;
	}
	public void setHarga_total(BigDecimal harga_total) {
		this.harga_total = harga_total;
	}
	 
	public String getLuas_tanah() {
		return luas_tanah;
	}
	public void setLuas_tanah(String luas_tanah) {
		this.luas_tanah = luas_tanah;
	}
	public BigDecimal getHarga_permeter() {
		return harga_permeter;
	}
	public void setHarga_permeter(BigDecimal harga_permeter) {
		this.harga_permeter = harga_permeter;
	}
	public BigDecimal getHarga_tanah() {
		return harga_tanah;
	}
	public void setHarga_tanah(BigDecimal harga_tanah) {
		this.harga_tanah = harga_tanah;
	}
	 
	public BigDecimal getTotal_penambahan() {
		return total_penambahan;
	}
	public void setTotal_penambahan(BigDecimal total_penambahan) {
		this.total_penambahan = total_penambahan;
	}
	public BigDecimal getHarga_lain() {
		return harga_lain;
	}
	public void setHarga_lain(BigDecimal harga_lain) {
		this.harga_lain = harga_lain;
	}
	public BigDecimal getHarga_appraisal() {
		return harga_appraisal;
	}
	public void setHarga_appraisal(BigDecimal harga_appraisal) {
		this.harga_appraisal = harga_appraisal;
	}
	public BigDecimal getCicilan_perbulan() {
		return cicilan_perbulan;
	}
	public void setCicilan_perbulan(BigDecimal cicilan_perbulan) {
		this.cicilan_perbulan = cicilan_perbulan;
	}
	 
	 
	public int getModel_bayar() {
		return model_bayar;
	}
	public void setModel_bayar(int model_bayar) {
		this.model_bayar = model_bayar;
	}
	public int getTenor() {
		return tenor;
	}
	public void setTenor(int tenor) {
		this.tenor = tenor;
	}
	public String getPenambahan_lain() {
		return penambahan_lain;
	}
	public void setPenambahan_lain(String penambahan_lain) {
		this.penambahan_lain = penambahan_lain;
	}
	public int getTgl_jatuhtempo() {
		return tgl_jatuhtempo;
	}
	public void setTgl_jatuhtempo(int tgl_jatuhtempo) {
		this.tgl_jatuhtempo = tgl_jatuhtempo;
	}


	
}