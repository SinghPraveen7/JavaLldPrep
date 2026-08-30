package org.practice.design.singleton;


/**
 * Why it's broken: Classic check-then-act race. Two threads both see instance == null, both construct, you get two instances.
 * The whole guarantee is gone under concurrency.
 */
public class LazyInitialization {

    private static LazyInitialization INSTANCE;

    private LazyInitialization() {
    }

    public static LazyInitialization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyInitialization();
        }
        return INSTANCE;
    }

}
