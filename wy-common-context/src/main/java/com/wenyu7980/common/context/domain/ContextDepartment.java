package com.wenyu7980.common.context.domain;

/**
 *
 * @author wenyu
 */
public class ContextDepartment {
    /** 部门id */
    private String id;
    /** 业务主体id */
    private String businessUnitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }
}
