package com.github.mrschyzo.repository

import com.github.mrschyzo.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskRepository : JpaRepository<Task, Int> {
    @Query("SELECT DISTINCT t FROM Task t JOIN t.assignees u ON u.id = :userId")
    fun getTasksByUserId(userId: Int) : List<Task>
}