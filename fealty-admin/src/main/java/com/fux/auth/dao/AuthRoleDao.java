package com.fux.auth.dao;

import com.fux.auth.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/03/30 15:40
 */
@Transactional
public interface AuthRoleDao extends JpaRepository<AuthRole, Long> {

    AuthRole findAllByRole(String role);

    @Query(value = "select r.* from auth_user u,auth_user_role ur,auth_role r where u.id = ur.user_id and r.id = ur.role_id and u.id=?1", nativeQuery = true)
    List<AuthRole> findByUserId(Long userId);

}
