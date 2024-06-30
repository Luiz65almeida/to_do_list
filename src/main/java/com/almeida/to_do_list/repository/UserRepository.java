package com.almeida.to_do_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almeida.to_do_list.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);

  Optional<Users> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
