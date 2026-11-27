package org.practice.design.practice.dynamic_price_evaluation;

import java.math.BigDecimal;

public final class PricingRule {
    private final String id;
    private final String name;
    private final RulePriority priority;
    private final Condition condition;
    private final Action action;
    private final boolean active;

    public PricingRule(String id, String name, RulePriority priority,
                       Condition condition, Action action, boolean active) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.condition = condition;
        this.action = action;
        this.active = active;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public RulePriority getPriority() { return priority; }
    public boolean isActive() { return active; }

    public boolean matches(PricingContext context) {
        return condition.evaluate(context);
    }

    public BigDecimal apply(PricingContext context, BigDecimal currentFare) {
        return action.apply(context, currentFare);
    }
}
