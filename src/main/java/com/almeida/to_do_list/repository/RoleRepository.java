package com.almeida.to_do_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almeida.to_do_list.common.enuns.RoleEnum;
import com.almeida.to_do_list.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(RoleEnum name);

}