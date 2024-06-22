package com.almeida.to_do_list.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almeida.to_do_list.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);

  List<Users> findByName(String name);

}
