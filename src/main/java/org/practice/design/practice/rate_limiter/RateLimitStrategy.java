package org.practice.design.practice.rate_limiter;

public interface RateLimitStrategy {

    boolean isRequestAllowed(String clientIp);

}
