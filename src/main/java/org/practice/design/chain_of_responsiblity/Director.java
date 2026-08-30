package org.practice.design.chain_of_responsiblity;

public class Director extends LeaveApprover {
    @Override
    public void approve(LeaveRequest leaveRequest) {
        if (leaveRequest.getDays() <= 30) {
            System.out.println("Director approved " + leaveRequest.getDays() + " leaves.");
        } else {
            System.out.println("Can't approve " + leaveRequest.getDays() + " leaves.");
        }
    }
}
