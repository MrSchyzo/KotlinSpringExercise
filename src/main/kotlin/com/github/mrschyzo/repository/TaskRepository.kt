package com.github.mrschyzo.repository

import com.github.mrschyzo.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskRepository : JpaRepository<Task, Int> {
    @Query("SELECT t FROM Task t WHERE :userId IN t.assignees")
    fun getTasksByUserId(userId: Int) : List<Task>
}