package com.wenyu7980.common.context.domain;

import java.util.Optional;

/**
 *
 * @author wenyu
 */
public class ContextUtils {
    private static final ThreadLocal<ContextInfo> LOCALS = new InheritableThreadLocal<>();

    public static void set(ContextInfo info) {
        LOCALS.set(info);
    }

    public static void remove() {
        LOCALS.remove();
    }

    public static String userId() {
        return LOCALS.get() == null ? null : LOCALS.get().getUserId();
    }

    public static void initSystem() {
        LOCALS.set(new ContextInfo());
    }

    public static Optional<ContextInfo> get() {
        return Optional.ofNullable(LOCALS.get());
    }
}
