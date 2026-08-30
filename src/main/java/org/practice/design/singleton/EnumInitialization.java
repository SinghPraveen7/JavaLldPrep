package org.practice.design.singleton;

/**
 * Why enums are the strongest singleton: They solve two attacks the other versions are vulnerable to, for free:
 * <p>
 * Reflection attack. With a private constructor, an attacker can do constructor.setAccessible(true) and call it to create a second instance
 * — breaking your singleton. The JVM specially forbids reflective instantiation of enums (newInstance on an enum throws IllegalArgumentException).
 * Enums are immune by language rule.
 * Serialization attack. A normal serializable singleton, when deserialized, creates a new object — another way to get a second instance.
 * To prevent it you must implement readResolve() to return the existing instance.
 * Enums handle serialization specially and guarantee the same instance survives a round-trip automatically.
 * <p>
 * So enum gives you single-instance, thread-safe (eager, via the same class-init guarantee), serialization-safe, and reflection-safe — in three lines.
 * The downside: it's eager (no lazy option), and it can't extend a class (enums implicitly extend java.lang.Enum), though it can implement interfaces.
 */
public enum EnumInitialization {

    INSTANCE;

    public void connect() {
        System.out.println("Connecting...");
    }

}

class DBConnection {
    EnumInitialization conn = EnumInitialization.INSTANCE;

    public void connect() {
        conn.connect();
    }
}
