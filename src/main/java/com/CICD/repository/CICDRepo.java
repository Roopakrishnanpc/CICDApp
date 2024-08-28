package com.CICD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CICD.entity.Users;
@Repository
public interface CICDRepo extends JpaRepository<Users, Integer> {

}
