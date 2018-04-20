package com.fux.auth.entity;

import javax.persistence.*;

/**
 * Created by fuxiaoj on 2018/04/11 11:17
 */
@Entity
@Table(name="auth_role_permission")
public class AuthRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="permission_id")
    private Long permissionId;

    @Column(name="role_id")
    private Long roleId;

    public AuthRolePermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
