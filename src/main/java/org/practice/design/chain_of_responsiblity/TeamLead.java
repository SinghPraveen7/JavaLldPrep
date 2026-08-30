package org.practice.design.chain_of_responsiblity;

public class TeamLead extends LeaveApprover {
    @Override
    public void approve(LeaveRequest leaveRequest) {
        if (leaveRequest.getDays() <= 2) {
            System.out.println("Team lead approved " + leaveRequest.getDays() + " leaves.");
        } else {
            System.out.println("Team lead passed your request to upper level...");
            this.nextApprover.approve(leaveRequest);
        }
    }
}
