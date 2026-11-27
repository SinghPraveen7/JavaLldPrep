package org.practice.design.practice.dynamic_price_evaluation;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DriverClass {

    public static void main(String[] args) throws InterruptedException {
        RuleRegistry registry = new RuleRegistry();
        PricingEngine pricingEngine = new PricingEngine(registry);

        // Manager define own conditions
        Condition isCar = pricingContext -> pricingContext.getVehicleType() == VehicleType.CAR;
        Condition isBike = pricingContext -> pricingContext.getVehicleType() == VehicleType.BIKE;
        Condition isTruck = pricingContext -> pricingContext.getVehicleType() == VehicleType.TRUCK;
        Condition isNight = pricingContext -> pricingContext.getEntryTime().getHour() >= 22
                || pricingContext.getEntryTime().getHour() < 6;
        Condition isWeekend = pricingContext -> {
            DayOfWeek day = pricingContext.getEntryTime().getDayOfWeek();
            return (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
        };
        Condition isFestival = PricingContext::isFestival;

        // Manager Configure Dynamic rules
        registry.addOrUpdateRule(new PricingRule("BASE_CAR_RULE_V1", "BASE_PRICE_CAR", RulePriority.BASE, isCar,
                (pricingContext, fare) -> fare.add(BigDecimal.valueOf(pricingContext.getBilledHours() * 20L)),
                true));
        registry.addOrUpdateRule(new PricingRule("BASE_BIKE_RULE_V1", "BASE_PRICE_BIKE", RulePriority.BASE, isBike,
                (pricingContext, fare) -> fare.add(BigDecimal.valueOf(pricingContext.getBilledHours() * 10L)),
                true));
        registry.addOrUpdateRule(new PricingRule("BASE_TRUCK_RULE_V1", "BASE_PRICE_TRUCK", RulePriority.BASE, isTruck,
                (pricingContext, fare) -> fare.add(BigDecimal.valueOf(pricingContext.getBilledHours() * 40L)),
                true));
        registry.addOrUpdateRule(new PricingRule("WEEKEND_RULE_V1", "WEEKEND_RULE", RulePriority.SURCHARGE, isWeekend,
                (pricingContext, fare) -> fare.multiply(BigDecimal.valueOf(1.25)),
                true)); // 25% more fare on weekends
        registry.addOrUpdateRule(new PricingRule("NIGHT_RULE_V1", "NIGHT_RULE", RulePriority.SURCHARGE, isNight,
                (pricingContext, fare) -> fare.multiply(BigDecimal.valueOf(1.10)),
                true)); // 10% more fare in night
        registry.addOrUpdateRule(new PricingRule("FESTIVAL_RULE_V1", "FESTIVAL_RULE", RulePriority.SURCHARGE, isFestival,
                (pricingContext, fare) -> fare.multiply(BigDecimal.valueOf(1.50)),
                true)); // 50% more fare during festivals
        registry.addOrUpdateRule(new PricingRule("UPPER_CAP_CAR_RULE_V1", "UPPER_CAP_CAR_RULE", RulePriority.CAP, isCar,
                (pricingContext, fare) -> {
                    BigDecimal maxCap = BigDecimal.valueOf(pricingContext.getBilledDays() * 200L);
                    return fare.min(maxCap);
                }, true)); // Car max cap per day is 200

        // --- Test Scenario 1: Car parked 5 hours on a Saturday night during a festival ---
        // Base: 5 * 20 = 100 | Night: *1.10 (110) | Weekend: *1.2 (122) | Festival: +100 (222) | Cap: min(222, 200) = 200.00
        PricingContext carCtx = new PricingContext(
                VehicleType.CAR,
                LocalDateTime.of(2026, 11, 28, 23, 0), // Saturday 11 PM
                LocalDateTime.of(2026, 11, 29, 6, 0),  // Sunday 6 AM (7 hours)
                true
        );
        System.out.println("Car Fare (capped): " + pricingEngine.calculateFare(carCtx));

        // --- Test Scenario 2: Concurrency simulation (Cars exiting while Manager updates rules) ---
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 3 Reader threads (cars exiting)
        for (int i = 1; i <= 3; i++) {
            int carId = i;
            executor.submit(() -> {
                BigDecimal fare = pricingEngine.calculateFare(carCtx);
                System.out.println("Thread Car-" + carId + " calculated fare: " + fare);
            });
        }

        // 1 Writer thread (manager raising the daily cap to 300)
        executor.submit(() -> {
            registry.addOrUpdateRule(new PricingRule(
                    "UPPER_CAP_CAR_RULE_V1", "Car Max 300/day", RulePriority.CAP,
                    isCar,
                    (ctx, fare) -> fare.min(BigDecimal.valueOf(ctx.getBilledDays() * 300L)),
                    true
            ));
            System.out.println("Manager updated Car Daily Cap to 300.");
        });

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Car Fare after manager update: " + pricingEngine.calculateFare(carCtx));


    }


}
