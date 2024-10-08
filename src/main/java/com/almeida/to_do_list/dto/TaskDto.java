package com.almeida.to_do_list.dto;

import com.almeida.to_do_list.common.enuns.PriorityLevelEnum;

public class TaskDto {

    private Long id;

    private String name;

    private String description;

    private boolean priority;

    public PriorityLevelEnum priorityLevel;

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}