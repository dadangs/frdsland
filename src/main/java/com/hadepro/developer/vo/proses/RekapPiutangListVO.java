package com.hadepro.developer.vo.proses;

import java.util.List;

import com.hadepro.developer.model.Rekap_piutang;



public class RekapPiutangListVO {
	private int pagesCount;
	private long totalRekaps;

	private String actionMessage;
	private String searchMessage;

	private List<Rekap_piutang> Rekap_piutangs;

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalRekaps() {
		return totalRekaps;
	}

	public void setTotalRekaps(long totalRekaps) {
		this.totalRekaps = totalRekaps;
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

	public List<Rekap_piutang> getRekap_piutangs() {
		return Rekap_piutangs;
	}

	public void setRekap_piutangs(List<Rekap_piutang> rekap_piutangs) {
		Rekap_piutangs = rekap_piutangs;
	}
}
