package com.almeida.to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almeida.to_do_list.model.Task;

public interface UserRepository extends JpaRepository<Task, Long> {

}
