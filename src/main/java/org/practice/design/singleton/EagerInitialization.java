package org.practice.design.singleton;

/**
 * Why this works and is thread-safe for free: The JVM guarantees that static initializers run exactly once,
 * at class initialization time, and the JVM serializes class initialization with a lock.
 * So INSTANCE is created once before any thread can call getInstance(). No synchronization needed in your code — the classloader already did it.
 * The "why not": It's eager — the instance is built when the class loads, even if you never use it.
 * If construction is expensive (opens a connection, reads a file) and the singleton is sometimes never needed,
 * you pay that cost for nothing. That's the only real knock against it.
 * For cheap singletons, this is a perfectly good answer and you should say so — don't reach for clever lazy versions when eager suffices.
 */
public class EagerInitialization {

    private static final EagerInitialization INSTANCE = new EagerInitialization();

    private EagerInitialization() {
    }

    public static EagerInitialization getInstance() {
        return INSTANCE;
    }

}
