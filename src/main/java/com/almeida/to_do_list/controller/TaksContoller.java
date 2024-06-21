package com.almeida.to_do_list.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almeida.to_do_list.dto.TaskDto;
import com.almeida.to_do_list.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaksContoller {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        List<TaskDto> dtoList = taskService.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        TaskDto dto = taskService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> insert(@RequestBody TaskDto taskDto) {
        TaskDto dto = taskService.insert(taskDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return taskService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        TaskDto dto = taskService.update(id, taskDto);
        return ResponseEntity.ok().body(dto);
    }
}
