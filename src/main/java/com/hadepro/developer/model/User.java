package com.hadepro.developer.model;


public class User {


    private int userid;
    
    private String email;
    private String name;
    private String enabled;
    private String password;
    private String role_desciption;
    private int role_id;
     

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_desciption() {
		return role_desciption;
	}

	public void setRole_desciption(String role_desciption) {
		this.role_desciption = role_desciption;
	}

	private Role role;

    
    public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}