package org.practice.design.practice.dynamic_price_evaluation;

import java.math.BigDecimal;
import java.util.List;

public class PricingEngine {

    private final RuleRegistry registry;

    public PricingEngine(RuleRegistry registry) {
        this.registry = registry;
    }

    public BigDecimal calculateFare(PricingContext context) {
        List<PricingRule> rules = registry.getActiveRulesSorted();
        BigDecimal fare = BigDecimal.ZERO;
        for (PricingRule rule: rules) {
            if (rule.matches(context)) fare = rule.apply(context, fare);
        }
        return fare.abs();
    }
}
