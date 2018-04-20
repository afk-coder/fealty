package com.fux.auth.dao;


import com.fux.auth.entity.AuthJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fuxiaoj on 2018/04/16 10:42
 */
@Transactional
public interface AuthJobDao extends JpaRepository<AuthJob, Long> {
}
