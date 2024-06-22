package com.almeida.to_do_list.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.almeida.to_do_list.common.exeption.ResourceBadRequestException;
import com.almeida.to_do_list.common.exeption.ResourceNotFoundException;
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

        if (optTask.isEmpty()) {
            throw new ResourceBadRequestException("Não foi possível encontrar a task com o id: " + id);
        }
        return modelMapper.map(optTask.get(), TaskDto.class);
    }

    public TaskDto insert(TaskDto taskDto) {
        validateName(taskDto);

        Optional<Task> optTask = taskRepository.findByName(taskDto.getName());

        if (optTask.isPresent()) {

            throw new ResourceBadRequestException("Já existe uma Task cadastrado com esse nome: " + taskDto.getName());
        }

        Task task = modelMapper.map(taskDto, Task.class);
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDto.class);
    }

    public ResponseEntity<String> delete(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (id == null || !task.isPresent()) {
            throw new ResourceNotFoundException("Não foi possível encontar o usuário com o id: " + id);
        }

        taskRepository.deleteById(id);
        return new ResponseEntity<>("Task deletada com sucesso", HttpStatus.OK);
    }

    public TaskDto update(Long id, TaskDto taskDto) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a task com o id: " + id);
        }

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

    private void validateName(TaskDto taskDto) {

        if (taskDto.getName() == null || taskDto.getPriorityLevel() == null) {
            throw new RuntimeErrorException(null, "Name e getPriorityLevel são obrigatórios");
        }
    }
}