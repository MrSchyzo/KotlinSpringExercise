package com.github.mrschyzo.controller

import com.github.mrschyzo.error.TaskNotFoundException
import com.github.mrschyzo.model.Task
import com.github.mrschyzo.model.User
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import com.nhaarman.mockito_kotlin.*
import junit.framework.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers
import java.util.*

class RepoTaskControllerGetTasksByIdTest {
    @Test
    fun `when provided an existing task id, a converted DTO is returned`() {
        val task = Task(1, "Name", "Description", "Status", arrayListOf(
                User(1, arrayListOf())
        ))
        val tasks = mock<TaskRepository> {
            whenever(it.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(task))
        }
        val users = mock<UserRepository> {}
        val controller = RepoTaskController(tasks, users)

        val output = controller.getTaskById(1)

        Assert.assertEquals(task.description, output.description)
        Assert.assertEquals(task.name, output.name)
        Assert.assertEquals(task.id, output.id)
        Assert.assertEquals(task.status, output.status)
        Assert.assertEquals(task.assignees.map { it.id }.toList(), output.users.toList())
        verify(tasks, times(1)).findById(1)
        verifyNoMoreInteractions(users)
    }

    @Test(expected = TaskNotFoundException::class)
    fun `when provided a non-existing task id, an exception is thrown`() {
        val tasks = mock<TaskRepository> {
            whenever(it.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty())
        }
        val users = mock<UserRepository> {}
        val controller = RepoTaskController(tasks, users)

        controller.getTaskById(1)
        verify(tasks, times(1)).findById(1)
        verifyNoMoreInteractions(users)
    }
}