package com.github.mrschyzo.controller

import com.github.mrschyzo.model.Task
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import com.nhaarman.mockito_kotlin.*
import junit.framework.Assert
import org.junit.Test

class RepoTaskControllerGetTasksByUserTest {
    @Test
    fun `when task repository returns a collection of users, their id is returned`() {
        val tasks = mock<TaskRepository> {
            whenever(it.getTasksByUserId(0)).thenReturn(arrayListOf(
                    Task(1, "", "", "", arrayListOf()),
                    Task(2, "", "", "", arrayListOf())
                )
            )
        }
        val users = mock<UserRepository> { }
        val controller = RepoTaskController(tasks, users)

        val ids = controller.getTasksByUser(0)

        Assert.assertEquals(arrayListOf(1, 2), ids)
        verify(tasks, times(1)).getTasksByUserId(0)
        verifyNoMoreInteractions(users)
    }

    @Test
    fun `when task repository returns an empty collection of users, an empty sequence is returned`() {
        val tasks = mock<TaskRepository> {
            whenever(it.getTasksByUserId(0)).thenReturn(arrayListOf())
        }
        val users = mock<UserRepository> { }
        val controller = RepoTaskController(tasks, users)

        val ids = controller.getTasksByUser(0)

        Assert.assertEquals(arrayListOf<Int>(), ids)
        verify(tasks, times(1)).getTasksByUserId(0)
        verifyNoMoreInteractions(users)
    }
}