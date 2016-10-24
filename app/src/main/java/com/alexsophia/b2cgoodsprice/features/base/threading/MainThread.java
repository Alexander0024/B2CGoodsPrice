package com.alexsophia.b2cgoodsprice.features.base.threading;

/**
 * UI主线程
 * <p/>
 */
public interface MainThread {
    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);
}
