package com.wenyu7980.common.context;

import com.google.gson.Gson;
import com.wenyu7980.common.context.domain.ContextInfo;
import com.wenyu7980.common.context.domain.ContextUtils;
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
public class ContextHandlerInterceptor implements HandlerInterceptor {
    private static final Gson GSON = GsonUtil.gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
        ContextUtils.set(GSON.fromJson(request.getHeader("context"), ContextInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
        ContextUtils.remove();
    }
}
