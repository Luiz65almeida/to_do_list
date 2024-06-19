package com.almeida.to_do_list.model;

import com.almeida.to_do_list.common.enuns.PriorityLevelEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    boolean priority;

    public PriorityLevelEnum priorityLevel;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public PriorityLevelEnum getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevelEnum priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}