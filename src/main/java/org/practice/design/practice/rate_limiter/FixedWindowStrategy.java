package org.practice.design.practice.rate_limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitStrategy {

    final static int CAPACITY = 2;
    final static int TIME_WINDOW = 1; // Per second 2 Tokens
    Map<String, Window> cachedData;
    
    class Window {
        int count;
        long lastFilledTime;

        public void refill() {
            long now = System.currentTimeMillis();
            long timeElaspedSec = (now - this.lastFilledTime) / 1000;
            if (timeElaspedSec >= TIME_WINDOW) {
                System.out.println("Refilling...");
                this.count = CAPACITY;
                this.lastFilledTime = now;
            }
        }
    }
    
    public FixedWindowStrategy() {
        this.cachedData = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String clientIp) {
        Window window = getOrCreateWindow(clientIp);
        window.refill();
        if (window.count < 1) return false;
        window.count = window.count - 1;
        return true;
    }

    private Window getOrCreateWindow(String clientIp) {
        Window window = cachedData.get(clientIp);
        if (window != null) return window;
        Window newWindow = new Window();
        newWindow.count = CAPACITY;
        newWindow.lastFilledTime = System.currentTimeMillis();
        cachedData.put(clientIp, newWindow);
        return newWindow;
    }

}
