package com.almeida.to_do_list.model;

import com.almeida.to_do_list.common.enuns.PriorityLevelEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean priority;

    @Enumerated(EnumType.STRING)
    private PriorityLevelEnum priorityLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}