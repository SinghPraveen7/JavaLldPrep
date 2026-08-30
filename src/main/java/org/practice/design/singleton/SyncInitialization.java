package org.practice.design.singleton;

/**
 * Why it's correct: The lock serializes the check-then-act, so only one thread can create it.
 * Why it's suboptimal: You acquire the lock on every single call forever, but the race only exists once — during the very first initialization.
 * After that, you're paying synchronization cost on millions of reads for a write that already happened.
 * In a hot path this is real contention. This is what double-checked locking tries to fix.
 *
 */
public class SyncInitialization {

    private static SyncInitialization INSTANCE;

    private SyncInitialization() {
    }

    public static synchronized SyncInitialization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SyncInitialization();
        }
        return INSTANCE;
    }
}
