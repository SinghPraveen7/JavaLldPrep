package org.practice.design.chain_of_responsiblity;

/**
 * The Chain of Responsibility pattern is a behavioral design pattern where a request is passed through a chain of handlers until one of them handles it.
 * Each handler decides whether it can process the request; if not, it forwards the request to the next handler in the chain.
 * This pattern helps decouple the sender of a request from its receiver and makes the system more flexible and extensible.
 * A common real-world example is Spring Security's filter chain,
 * where authentication, authorization, and logging filters process an incoming request sequentially before it reaches the controller.
 */
public class CORMain {
    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest(10);
        TeamLead teamLead = new TeamLead();
        Manager manager = new Manager();
        Director director = new Director();
        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);
        teamLead.approve(leaveRequest);
        LeaveRequest leaveRequest2 = new LeaveRequest(40);
        teamLead.approve(leaveRequest2);
    }
}
