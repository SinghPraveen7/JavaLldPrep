package org.practice.design.practice.dynamic_price_evaluation;

import java.time.Duration;
import java.time.LocalDateTime;

public final class PricingContext {
    private final VehicleType vehicleType;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final boolean isFestival;

    public PricingContext(VehicleType vehicleType, LocalDateTime entryTime,
                          LocalDateTime exitTime, boolean isFestival) {
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isFestival = isFestival;
    }

    public VehicleType getVehicleType() { return vehicleType; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public boolean isFestival() { return isFestival; }

    // Rounds up partial hours (minimum 1 hour)
    public long getBilledHours() {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return Math.max(1, (long) Math.ceil(minutes / 60.0));
    }

    // Rounds up partial days (minimum 1 day)
    public long getBilledDays() {
        long hours = getBilledHours();
        return Math.max(1, (long) Math.ceil(hours / 24.0));
    }
}
