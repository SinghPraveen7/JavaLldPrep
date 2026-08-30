package org.practice.design.practice.rate_limiter;

public class RateLimiter {

    RateLimitStrategy strategy;

    public RateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean isRequestAllowed(String clientIp) {
        return strategy.isRequestAllowed(clientIp);
    }

}
