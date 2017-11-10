package com.hadepro.developer.vo.master;

import java.util.List;

import com.hadepro.developer.model.Developer;
import com.hadepro.developer.model.Konsumen;
import com.hadepro.developer.model.User;

public class UserListVO {
	private int pagesCount;    
	 public long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}


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

	 
	 	private long totalUser;
		 


		private String searchMessage;

	    private List<User> user;

	    
		public UserListVO() {
	    }
	 
}
