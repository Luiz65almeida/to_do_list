package com.almeida.to_do_list.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeida.to_do_list.dto.TaskDto;
import com.almeida.to_do_list.model.Task;
import com.almeida.to_do_list.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto findById(Long id) {
        Optional<Task> optTask = taskRepository.findById(id);
        return modelMapper.map(optTask.get(), TaskDto.class);
    }

    public TaskDto insert(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        task = taskRepository.save(task); // Salva a entidade no repositório
        return modelMapper.map(task, TaskDto.class);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto update(Long id, TaskDto taskDto) {
        Task entity = taskRepository.getReferenceById(id);
        updateTaskDetails(entity, taskDto);
        Task updatedEntity = taskRepository.save(entity);
        return modelMapper.map(updatedEntity, TaskDto.class);
    }

    private void updateTaskDetails(Task entity, TaskDto taskDto) {
        entity.setName(taskDto.getName());
        entity.setDescription(taskDto.getDescription());
        entity.setPriority(taskDto.isPriority());
        entity.setPriorityLevel(taskDto.getPriorityLevel());
    }
}