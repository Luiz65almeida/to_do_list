package com.almeida.to_do_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almeida.to_do_list.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  Optional<Task> findByName(String name);
}
