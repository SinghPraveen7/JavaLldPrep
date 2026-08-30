package org.practice.design.chain_of_responsiblity;

public abstract class LeaveApprover {

    LeaveApprover nextApprover;

    public void setNextApprover(LeaveApprover leaveApprover) {
        this.nextApprover = leaveApprover;
    }

    public abstract void approve(LeaveRequest leaveRequest);

}
