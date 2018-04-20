package com.fux.auth.dao;

import com.fux.auth.entity.AuthPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/04 14:21
 */
@Transactional
public interface AuthPermissionDao extends JpaRepository<AuthPermission, Long> {

    @Query(value = "select p.* from auth_permission p,auth_role_permission rp, auth_user_role ur where p.id = rp.permission_id and rp.role_id = ur.role_id and ur.user_id =?1 ", nativeQuery = true)
    List<AuthPermission> findByUserId(Long userId);

    @Query(value = "select p.* from auth_permission p,auth_role_permission rp where p.id = rp.permission_id and rp.role_id = ?1", nativeQuery = true)
    List<AuthPermission> findByRoleId(Long roleId);


}
