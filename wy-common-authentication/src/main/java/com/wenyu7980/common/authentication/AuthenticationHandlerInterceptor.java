package com.wenyu7980.common.authentication;

import com.google.gson.Gson;
import com.wenyu7980.common.authentication.util.AuthenticationInfo;
import com.wenyu7980.common.authentication.util.AuthenticationUtils;
import com.wenyu7980.common.gson.adapter.GsonUtil;
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
    private static final Gson GSON = GsonUtil.gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
        AuthenticationUtils.set(GSON.fromJson(request.getHeader("authInfo"), AuthenticationInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
        AuthenticationUtils.remove();
    }
}
