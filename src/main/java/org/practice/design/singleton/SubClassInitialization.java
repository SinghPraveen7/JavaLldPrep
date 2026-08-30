package org.practice.design.singleton;

/**
 * Why this is brilliant: It exploits the same JVM guarantee as eager init, but lazily.
 * A nested class is not loaded/initialized until it's first referenced. Holder is only touched inside getInstance(),
 * so INSTANCE isn't created until the first call — that's the laziness. And because it's a static initializer,
 * the JVM's class-init lock provides thread safety for free, with zero synchronization in your code and zero cost on subsequent reads.
 * You get DCL's performance and eager's simplicity, with none of the volatile subtlety.
 * The only thing it can't do is lazy init based on a runtime parameter (the holder takes no arguments).
 *
 */
public class SubClassInitialization {

    private static class InstanceHolder {
        private static final SubClassInitialization INSTANCE = new SubClassInitialization();
    }

    private SubClassInitialization() {

    }

    public static SubClassInitialization getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
