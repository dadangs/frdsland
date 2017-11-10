package com.hadepro.developer.controller;

/**
 * Spring bean
 * 
 */
public class HelloWorld {
	private String name;
        private String schema;
        private String prosen_admin;
        private String maxpage;

    public String getProsen_admin() {
        return prosen_admin;
    }

    public void setProsen_admin(String prosen_admin) {
        this.prosen_admin = prosen_admin;
    }
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Spring 3 : Hello ! " + name);
	}

	public String getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(String maxpage) {
		this.maxpage = maxpage;
	}

	
}