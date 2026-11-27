package org.practice.design.practice.dynamic_price_evaluation;

@FunctionalInterface
public interface Condition {
    boolean evaluate(PricingContext context);
}
