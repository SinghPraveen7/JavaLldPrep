package org.practice.design.practice.rate_limiter;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        TokenBucketStrategy tokenBucketStrategy = new TokenBucketStrategy();
        RateLimiter rateLimiter = new RateLimiter(tokenBucketStrategy);
        boolean isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        Thread.sleep(2000);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        System.out.println("======================================================");
        FixedWindowStrategy fixedWindowStrategy = new FixedWindowStrategy();
        rateLimiter = new RateLimiter(fixedWindowStrategy);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        Thread.sleep(1000);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);
        isAllowed = rateLimiter.isRequestAllowed("10.121.43.82");
        System.out.println("Is Request allowed: " + isAllowed);

    }

}
