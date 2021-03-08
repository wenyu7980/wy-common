package com.wenyu7980.common.context;

import com.google.gson.Gson;
import com.wenyu7980.common.context.domain.ContextInfo;
import com.wenyu7980.common.context.domain.ContextUtils;
import com.wenyu7980.common.gson.adapter.GsonUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author wenyu
 */
@Component
public class HeaderRequestInterceptor implements RequestInterceptor {
    private static final Gson GSON = GsonUtil.gson();

    @Override
    public void apply(RequestTemplate template) {
        template.header("context", GSON.toJson(ContextUtils.get().orElse(new ContextInfo())));
    }
}
