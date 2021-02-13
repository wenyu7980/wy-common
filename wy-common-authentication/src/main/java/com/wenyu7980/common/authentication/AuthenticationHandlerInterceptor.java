package com.wenyu7980.common.authentication;

import com.wenyu7980.common.authentication.util.AuthenticationInfo;
import com.wenyu7980.common.authentication.util.AuthenticationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wenyu
 */
@Component
public class AuthenticationHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
        AuthenticationUtils.set(new AuthenticationInfo(request.getHeader("userId")));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
        AuthenticationUtils.remove();
    }
}
