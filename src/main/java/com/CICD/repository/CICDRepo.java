package com.CICD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CICD.entity.Users;

public interface CICDRepo extends JpaRepository<Users, Integer> {

}
