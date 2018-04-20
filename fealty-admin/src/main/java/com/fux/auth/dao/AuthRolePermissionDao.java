package com.fux.auth.dao;

import com.fux.auth.entity.AuthRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/11 11:34
 */
@Transactional
public interface AuthRolePermissionDao extends JpaRepository<AuthRolePermission, Long> {

    @Query(value = "select * from auth_role_permission where role_id = ?1", nativeQuery = true)
    List<AuthRolePermission> findByRoleId(Long roleId);
}

