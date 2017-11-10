package com.hadepro.developer.vo.master;

import java.util.List;

import com.hadepro.developer.model.Produk;

public class ProdukListVO {
	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalProduks() {
		return totalProduks;
	}

	public void setTotalProduks(long totalProduks) {
		this.totalProduks = totalProduks;
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

	public List<Produk> getProduks() {
		return produks;
	}

	public void setProduks(List<Produk> produks) {
		this.produks = produks;
	}

	private int pagesCount;
	private long totalProduks;
	private String actionMessage;
	private String searchMessage;

	private List<Produk> produks;

	public ProdukListVO() {
	}
}
