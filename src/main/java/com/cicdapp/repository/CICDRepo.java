package com.cicdapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cicdapp.entity.Users;
@Repository
public interface CICDRepo extends JpaRepository<Users, Integer> {

}
