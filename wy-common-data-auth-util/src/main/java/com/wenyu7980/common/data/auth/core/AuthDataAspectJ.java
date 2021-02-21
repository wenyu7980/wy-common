package com.wenyu7980.common.data.auth.core;

import com.wenyu7980.common.data.auth.annotation.AuthData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 *
 * @author wenyu
 */
public class AuthDataAspectJ {
    private BiFunction<String, Object, Boolean> authCheckFunction;

    public AuthDataAspectJ(BiFunction<String, Object, Boolean> authCheckFunction) {
        this.authCheckFunction = authCheckFunction;
    }

    @Pointcut("@annotation(com.wenyu7980.common.data.auth.annotation.AuthData)")
    public void authDataMethod() {
    }

    @AfterReturning(pointcut = "authDataMethod() && @annotation(authData)", returning = "ret")
    public void authDataAspect(JoinPoint joinPoint, Object ret, AuthData authData) {
        if (Objects.isNull(ret)) {
            return;
        }
        if (ret instanceof Optional && !((Optional) ret).isPresent()) {
            return;
        }
    }

    private boolean check(Object object) throws IllegalAccessException {
        if (Objects.isNull(object)) {
            return false;
        }
        String resource = null;
        AuthData authData = object.getClass().getDeclaredAnnotation(AuthData.class);
        if (Objects.nonNull(authData)) {
            resource = authData.resource();
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            AuthData fieldAnnotation = field.getDeclaredAnnotation(AuthData.class);
            if (Objects.isNull(fieldAnnotation)) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(object);
            if (Objects.isNull(value)) {
                continue;
            }
            if (value.getClass().isPrimitive() && this.authCheckFunction.apply(resource, value)) {
                return true;
            } else if (check(value)) {
                return true;
            }

        }
        return false;
    }

}
