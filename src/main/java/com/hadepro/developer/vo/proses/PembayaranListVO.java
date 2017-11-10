package com.hadepro.developer.vo.proses;

import java.util.List;

import com.hadepro.developer.model.Pembayaran;
import com.hadepro.developer.model.Transaksi;

public class PembayaranListVO {
	private int pagesCount;
	private long totalPembayarans;
	 

	public long getTotalPembayarans() {
		return totalPembayarans;
	}

	public void setTotalPembayarans(long totalPembayarans) {
		this.totalPembayarans = totalPembayarans;
	}

	private String actionMessage;
	private String searchMessage;

	private List<Pembayaran> pembayarans;

	public List<Pembayaran> getPembayarans() {
		return pembayarans;
	}

	public void setPembayarans(List<Pembayaran> pembayarans) {
		this.pembayarans = pembayarans;
	}

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

	

	public PembayaranListVO() {
	}
}
