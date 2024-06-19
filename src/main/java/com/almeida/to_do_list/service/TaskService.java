package com.almeida.to_do_list.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeida.to_do_list.model.Task;
import com.almeida.to_do_list.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = taskRepository.findById(id);
        return obj.get();
    }

    public Task insert(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Long id, Task obj) {
        Task entity = taskRepository.getReferenceById(id);
        updateTaskDetails(entity, obj);
        return taskRepository.save(entity);
    }

    private void updateTaskDetails(Task entity, Task obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPriority(obj.isPriority());
        entity.setPriorityLevel(obj.getPriorityLevel());
    }
}