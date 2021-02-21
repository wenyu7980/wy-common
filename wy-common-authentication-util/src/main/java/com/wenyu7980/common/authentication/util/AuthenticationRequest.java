package com.wenyu7980.common.authentication.util;

import java.util.Objects;

/**
 *
 * @author wenyu
 */
public class AuthenticationRequest {
    private String serviceName;
    private String method;
    private String path;

    public AuthenticationRequest(String serviceName, String method, String path) {
        this.serviceName = serviceName;
        this.method = method;
        this.path = path;
    }

    private String getServiceName() {
        return serviceName;
    }

    private void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private String getMethod() {
        return method;
    }

    private void setMethod(String method) {
        this.method = method;
    }

    private String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(serviceName, that.serviceName) && Objects.equals(method, that.method) && Objects
          .equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, method, path);
    }
}
