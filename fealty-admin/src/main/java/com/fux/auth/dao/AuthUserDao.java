package com.fux.auth.dao;

import com.fux.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fuxiaoj on 2018/03/27 11:34
 */
public interface AuthUserDao extends JpaRepository<AuthUser, Long> {

    AuthUser findByUsername(String username);
}

