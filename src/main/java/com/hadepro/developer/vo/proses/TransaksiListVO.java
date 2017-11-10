package com.hadepro.developer.vo.proses;

import java.util.List;

import com.hadepro.developer.model.Transaksi;

 

public class TransaksiListVO {
	private int pagesCount;
	private long totalTransaksis;
	public long getTotalTransaksis() {
		return totalTransaksis;
	}

	public void setTotalTransaksis(long totalTransaksis) {
		this.totalTransaksis = totalTransaksis;
	}

	private String actionMessage;
	private String searchMessage;

	private List<Transaksi> transaksis;

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

	public List<Transaksi> getTransaksis() {
		return transaksis;
	}

	public void setTransaksis(List<Transaksi> transaksis) {
		this.transaksis = transaksis;
	}

	public TransaksiListVO() {
	}
}
