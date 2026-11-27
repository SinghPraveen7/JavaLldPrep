package org.practice.design.practice.dynamic_price_evaluation;

import java.math.BigDecimal;

@FunctionalInterface
public interface Action {
    BigDecimal apply(PricingContext context, BigDecimal currentFare);
}
