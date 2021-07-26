package com.example.todo.models;

public class Subtask {

    private int id;
    private String description;
    private Boolean status;
    private int task_id;

    public Subtask(int id, String description, Boolean status, int task_id) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.task_id = task_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
