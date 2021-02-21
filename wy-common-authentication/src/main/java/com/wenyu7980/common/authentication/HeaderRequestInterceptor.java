package com.wenyu7980.common.authentication;

import com.google.gson.Gson;
import com.wenyu7980.common.authentication.util.AuthenticationInfo;
import com.wenyu7980.common.authentication.util.AuthenticationUtils;
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
        template.header("authInfo", GSON.toJson(AuthenticationUtils.get().orElse(new AuthenticationInfo())));
    }
}
