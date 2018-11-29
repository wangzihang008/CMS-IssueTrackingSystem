package com.fdmgroup.Enum;

public enum Type {

	CUSTOMER("customer"), ADMIN("admin"), DEPADMIN("depadmin");
	
	private String name;
	
	private Type(String name) {
		this.name= name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
