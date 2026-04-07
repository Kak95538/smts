package com.example.stms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stms.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}