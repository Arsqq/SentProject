package com.example.eurekaclient1.Repo;

import com.example.eurekaclient1.Model.ERole;
import com.example.eurekaclient1.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
