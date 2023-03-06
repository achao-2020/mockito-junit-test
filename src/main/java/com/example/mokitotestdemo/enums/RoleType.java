package com.example.mokitotestdemo.enums;

/**
 * @author licc3
 * @date 2023-2-20 09:52
 */
public enum RoleType {
    NORMAL(0, "普通角色"),
    SYS_ADMIN(1, "系统管理员"),
    SYS_INSPECTION(2, "系统巡检员")
    ;


    public Integer value;

    public String typeName;

    RoleType(int value, String typeName) {
        this.value = value;
        this.typeName = typeName;
    }

    public static RoleType valueOf(Integer value) {
        for (RoleType type : RoleType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}
