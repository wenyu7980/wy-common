package com.wenyu7980.common.context.domain;

import java.util.*;

/**
 *
 * @author wenyu
 */
public class ContextInfo {
    /** 用户id */
    private String userId;
    /** 所属部门  */
    private Set<ContextDepartment> departments = new HashSet<>();
    /** matrix权限 */
    private Set<AuthenticationMatrix> matrices = new HashSet<>();
    /** 请求 */
    private ContextRequest request;
    /** 资源部门 */
    transient private Map<String, Set<String>> resourceDepartments = new HashMap<>();
    /** 权限部门 */
    transient private Map<PermissionKey, Set<String>> permissionDepartments = new HashMap<>();

    public ContextInfo() {
    }

    public ContextInfo(String userId, Set<ContextDepartment> departments,
      Set<AuthenticationMatrix> matrices, ContextRequest request) {
        this.userId = userId;
        this.departments = departments;
        this.request = request;
        this.setMatrices(matrices);
    }

    public String getUserId() {
        return userId;
    }

    public Set<ContextDepartment> getDepartments() {
        return departments;
    }

    public Set<AuthenticationMatrix> getMatrices() {
        return matrices;
    }

    public Set<String> getDepartmentByResource(String resource) {
        return Collections.unmodifiableSet(this.resourceDepartments.get(resource));
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    private void setDepartments(Set<ContextDepartment> departments) {
        this.departments = departments;
    }

    private void setMatrices(Set<AuthenticationMatrix> matrices) {
        this.matrices = matrices;
        for (AuthenticationMatrix matrix : matrices) {
            // 资源-部门
            if (!this.resourceDepartments.containsKey(matrix.getResource())) {
                this.resourceDepartments.put(matrix.getResource(), new HashSet<>());
            }
            this.resourceDepartments.get(matrix.getResource()).addAll(matrix.getDepartments());
            // 权限-部门
            PermissionKey permissionKey = new PermissionKey(matrix.getServiceName(), matrix.getMethod(),
              matrix.getPath());
            if (!this.permissionDepartments.containsKey(permissionKey)) {
                this.permissionDepartments.put(permissionKey, new HashSet<>());
            }
            this.permissionDepartments.get(permissionKey).addAll(matrix.getDepartments());
        }
    }

    protected static class PermissionKey {
        private String serviceName;
        private String method;
        private String path;

        public PermissionKey(String serviceName, String method, String path) {
            this.serviceName = serviceName;
            this.method = method;
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
            PermissionKey that = (PermissionKey) o;
            return Objects.equals(serviceName, that.serviceName) && Objects.equals(method, that.method) && Objects
              .equals(path, that.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serviceName, method, path);
        }
    }
}
