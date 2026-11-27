package org.practice.design.practice.dynamic_price_evaluation;

public enum RulePriority {
    BASE(1),
    SURCHARGE(2),
    DISCOUNT(3),
    CAP(4);

    private final int order;
    RulePriority(int order) { this.order = order; }
    public int getOrder() { return order; }
}
