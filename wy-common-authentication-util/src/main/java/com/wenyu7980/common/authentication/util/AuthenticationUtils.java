package com.wenyu7980.common.authentication.util;

/**
 *
 * @author wenyu
 */
public class AuthenticationUtils {
    private static final ThreadLocal<AuthenticationInfo> LOCALS = new InheritableThreadLocal<>();

    public static void set(AuthenticationInfo info) {
        LOCALS.set(info);
    }

    public static void remove() {
        LOCALS.remove();
    }

    public static String userId() {
        return LOCALS.get().getUserId();
    }

    public static void initSystem() {
        LOCALS.set(new AuthenticationInfo("1"));
    }
}
