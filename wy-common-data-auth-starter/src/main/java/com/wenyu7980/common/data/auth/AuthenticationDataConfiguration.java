package com.wenyu7980.common.data.auth;

import com.wenyu7980.common.context.domain.ContextUtils;
import com.wenyu7980.common.data.auth.core.AuthDataAspectJ;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 *
 * @author wenyu
 */
@Configuration
public class AuthenticationDataConfiguration {
    @Bean
    public AuthDataAspectJ authDataAspectJ(@Value("${application.auth.data.check:#{true}}") boolean check) {
        return new AuthDataAspectJ((resource, value) -> {
            if (!check) {
                return true;
            }
            if (ContextUtils.get().isPresent()) {
                return ContextUtils.get().get().getDepartmentByResource(resource).stream()
                  .anyMatch(v -> Objects.equals(v, value));
            }
            return false;
        });
    }
}
