package org.practice.design.chain_of_responsiblity;

public class Manager extends LeaveApprover {
    @Override
    public void approve(LeaveRequest leaveRequest) {
        if (leaveRequest.getDays() <= 5) {
            System.out.println("Manager approved " + leaveRequest.getDays() + " leaves.");
        } else {
            System.out.println("Manager passed your request to upper level...");
            this.nextApprover.approve(leaveRequest);
        }
    }
}
