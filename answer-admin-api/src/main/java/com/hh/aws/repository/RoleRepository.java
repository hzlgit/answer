package com.hh.aws.repository;

import com.hh.aws.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, String> {
}
