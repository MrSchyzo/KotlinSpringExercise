package com.github.mrschyzo.controller

import com.github.mrschyzo.error.TaskNotFoundException
import com.github.mrschyzo.model.Task
import com.github.mrschyzo.model.User
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import com.nhaarman.mockito_kotlin.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import java.util.*

class RepoTaskControllerTest {

    @Test
    fun `when task repository returns a collection of users, their id is returned`() {
        val tasks = mock<TaskRepository> {
            whenever(it.getTasksByUserId(0)).thenReturn(arrayListOf(
                    Task(1, "", "", "", arrayListOf()),
                    Task(2, "", "", "", arrayListOf())
                )
            )
        }
        val users = mock<UserRepository> {  }
        val controller = RepoTaskController(tasks, users)

        val ids = controller.getTasksByUser(0)

        assertEquals(arrayListOf(1, 2), ids)
        verify(tasks, times(1)).getTasksByUserId(0)
        verifyNoMoreInteractions(users)
    }

    @Test
    fun `when task repository returns an empty collection of users, an empty sequence is returned`() {
        val tasks = mock<TaskRepository> {
            whenever(it.getTasksByUserId(0)).thenReturn(arrayListOf())
        }
        val users = mock<UserRepository> {  }
        val controller = RepoTaskController(tasks, users)

        val ids = controller.getTasksByUser(0)

        assertEquals(arrayListOf<Int>(), ids)
        verify(tasks, times(1)).getTasksByUserId(0)
        verifyNoMoreInteractions(users)
    }

    @Test
    fun `when provided an existing task id, a converted DTO is returned`() {
        val task = Task(1, "Name", "Description", "Status", arrayListOf(
                User(1, arrayListOf())
        ))
        val tasks = mock<TaskRepository> {
            whenever(it.findById(anyInt())).thenReturn(Optional.of(task))
        }
        val users = mock<UserRepository> {}
        val controller = RepoTaskController(tasks, users)

        val output = controller.getTaskById(1)

        assertEquals(task.description, output.description)
        assertEquals(task.name, output.name)
        assertEquals(task.id, output.id)
        assertEquals(task.status, output.status)
        assertEquals(task.assignees.map {it.id}.toList(), output.users.toList())
        verify(tasks, times(1)).findById(1)
        verifyNoMoreInteractions(users)
    }

    @Test(expected = TaskNotFoundException::class)
    fun `when provided a non-existing task id, an exception is thrown`() {
        val tasks = mock<TaskRepository> {
            whenever(it.findById(anyInt())).thenReturn(Optional.empty())
        }
        val users = mock<UserRepository> {}
        val controller = RepoTaskController(tasks, users)

        controller.getTaskById(1)
        verify(tasks, times(1)).findById(1)
        verifyNoMoreInteractions(users)
    }

    @Test
    fun createTask() {

    }

    @Test
    fun closeTaskById() {
    }
}