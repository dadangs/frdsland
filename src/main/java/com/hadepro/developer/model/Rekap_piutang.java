package com.hadepro.developer.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Rekap_piutang {
	 @JsonInclude(JsonInclude.Include.NON_NULL) 
	Pembayaran pembayaran;
	
	public Pembayaran getPembayaran() {
		return pembayaran;
	}
	public void setPembayaran(Pembayaran pembayaran) {
		this.pembayaran = pembayaran;
	}
	public Pembayaran getPembayaran_dp() {
		return pembayaran_dp;
	}
	public void setPembayaran_dp(Pembayaran pembayaran_dp) {
		this.pembayaran_dp = pembayaran_dp;
	}
	Pembayaran pembayaran_dp;
}
