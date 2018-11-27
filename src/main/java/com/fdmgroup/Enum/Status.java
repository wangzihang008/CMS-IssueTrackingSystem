package com.fdmgroup.Enum;

public enum Status {

	RESOLVED, ASSIGNED, UNASSIGNED, REQUESTED, REJECTED, APPROVED, ACTIVE, INACTIVE, CUSTOMERUPDATE
	
	//RESOLVED - this issue is resolved by a department admin
	//ASSIGNED - this issue is assigned to a department admin
	//UNASSIGNED - this issue has not been assigned to any department admin yet
	//REQUESTED - a department admin requested a reassignment for this issue
	//REJECTED - this issue is rejected by a department admin
	//APPROVED - a resolved issue is approved by customer
	
}
