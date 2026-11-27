package org.practice.design.practice.dynamic_price_evaluation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RuleRegistry {

    private final Map<String, PricingRule> rules = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addOrUpdateRule(PricingRule rule) {
        lock.writeLock().lock();
        try {
            rules.put(rule.getId(), rule);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeRule(String ruleId) {
        lock.writeLock().lock();
        try {
            rules.remove(ruleId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Returns a consistent, sorted snapshot for lock-free evaluation
    public List<PricingRule> getActiveRulesSorted() {
        lock.readLock().lock();
        try {
            return rules.values().stream().filter(PricingRule::isActive)
                    .sorted(Comparator.comparingInt(pr -> pr.getPriority().getOrder()))
                    .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

}
