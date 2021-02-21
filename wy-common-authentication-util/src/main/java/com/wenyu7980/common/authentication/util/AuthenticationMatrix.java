package com.wenyu7980.common.authentication.util;

import java.util.Objects;
import java.util.Set;

/**
 *
 * @author wenyu
 */
public class AuthenticationMatrix {
    /** 服务名 */
    private String serviceName;
    /** 方法 */
    private String method;
    /** 路径 */
    private String path;
    /** 资源名 */
    private String resource;
    /** 可管理的部门 */
    private Set<String> departments;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Set<String> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<String> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticationMatrix that = (AuthenticationMatrix) o;
        return Objects.equals(serviceName, that.serviceName) && Objects.equals(method, that.method) && Objects
          .equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, method, path);
    }
}
