package com.hadepro.developer.vo.master;

import java.util.List;

import com.hadepro.developer.model.Developer;
import com.hadepro.developer.model.Konsumen;

public class DeveloperListVO {
	private int pagesCount;    
	 private String actionMessage;
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

	 
	 	private long totalDeveloper;
		public long getTotalDeveloper() {
			return totalDeveloper;
		}

		public void setTotalDeveloper(long totalDeveloper) {
			this.totalDeveloper = totalDeveloper;
		}


		private String searchMessage;

	    private List<Developer> developer;

	    public List<Developer> getDeveloper() {
			return developer;
		}

		public void setDeveloper(List<Developer> developer) {
			this.developer = developer;
		}

		public DeveloperListVO() {
	    }
	 
}
