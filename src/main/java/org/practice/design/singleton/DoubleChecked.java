package org.practice.design.singleton;

/**
 * The logic: check without locking (cheap), and only if it looks null do you take the lock and check again
 * (because another thread may have created it between your first check and acquiring the lock).
 * After initialization, every call hits the fast path with no locking.
 * Now the critical part — why volatile is not optional: instance = new Config() is not atomic. At the bytecode/CPU level it's roughly three steps:
 *
 * allocate memory for the object,
 * run the constructor to initialize it,
 * publish the reference — assign instance to point at that memory.
 *
 * The JVM and CPU are allowed to reorder steps 2 and 3 (out-of-order execution / instruction reordering is legal as long as it's invisible to a single thread).
 * So a thread could publish the reference (step 3) before the constructor finishes (step 2).
 * Now a second thread calls getInstance(), sees instance != null on the fast path, and returns a partially constructed object —
 * fields still at default values. Catastrophic and nearly impossible to reproduce in testing.
 * volatile fixes this two ways: it forbids that reordering (establishes a happens-before edge so the constructor's writes are complete before the reference
 * is visible), and it guarantees visibility across threads (no thread reads a stale cached null or stale reference).
 * This is the canonical real-world reason volatile exists, and saying it precisely is a strong senior signal.
 *
 */
public class DoubleChecked {

    private static volatile DoubleChecked INSTANCE;

    private DoubleChecked() {
    }

    public static DoubleChecked getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleChecked.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleChecked();
                }
            }
        }
        return INSTANCE;
    }

}
