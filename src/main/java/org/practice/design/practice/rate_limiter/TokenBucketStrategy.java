package org.practice.design.practice.rate_limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategy implements RateLimitStrategy {

    Map<String, Bucket> cachedData;
    final static int CAPACITY = 3;
    final static int RATE_PER_SECOND = 1;

    class Bucket {
        int availableToken;
        long lastUpdatedTimestamp;

        public void refill() {
            long now = System.currentTimeMillis();
            long timeElasped = now - this.lastUpdatedTimestamp;
            int tokenToRefill = (int) (timeElasped / 1000l) * RATE_PER_SECOND;
            if (tokenToRefill < 1) return;
            System.out.println("Refilling Token " + tokenToRefill + " in Bucket...");
            this.availableToken = Math.min(CAPACITY, tokenToRefill + availableToken);
            this.lastUpdatedTimestamp = now;
        }
    }

    public TokenBucketStrategy() {
        cachedData = new ConcurrentHashMap<>();
    }


    @Override
    public boolean isRequestAllowed(String clientIp) {
        Bucket userBucket = getOrCreateBucket(clientIp);
        synchronized (userBucket) {
            userBucket.refill();
            if (userBucket.availableToken < 1) {
                return false;
            }
            userBucket.availableToken = userBucket.availableToken - 1;
        }
        return true;
    }

    private Bucket getOrCreateBucket(String clientIp) {
       Bucket bucket = cachedData.get(clientIp);
       if (bucket != null) return bucket;
       Bucket newBucket = new Bucket();
       newBucket.availableToken = CAPACITY;
       newBucket.lastUpdatedTimestamp = System.currentTimeMillis();
       cachedData.put(clientIp, newBucket);
       return newBucket;
    }
}
