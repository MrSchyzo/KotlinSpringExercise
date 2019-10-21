package com.github.mrschyzo.controller

import com.github.mrschyzo.error.TaskDoesNotBelongToUserException
import com.github.mrschyzo.error.TaskNotFoundException
import com.github.mrschyzo.model.Task
import com.github.mrschyzo.model.User
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class RepoTaskControllerCloseTaskByIdTest {
    @Test(expected = TaskNotFoundException::class)
    fun `when task is not found by id, throw TaskNotFoundException`() {
        val tasks = mock<TaskRepository> {
            whenever(it.findById(0)).thenReturn(Optional.empty())
        }
        val users = mock<UserRepository> { }
        val controller = RepoTaskController(tasks, users)

        controller.closeTaskById(0, 0)
    }

    @Test(expected = TaskDoesNotBelongToUserException::class)
    fun `when task exists but does not belong to user, throw TaskDoesNotBelongToUserException`() {
        val task = Task(1, "", "", "Open", arrayListOf(User(0, arrayListOf())))
        val tasks = mock<TaskRepository> {
            whenever(it.findById(1)).thenReturn(Optional.of(task))
        }
        val users = mock<UserRepository> { }
        val controller = RepoTaskController(tasks, users)

        controller.closeTaskById(1, 5)
        verify(tasks, times(1)).findById(1)
        verifyNoMoreInteractions(tasks)
        verifyNoMoreInteractions(users)
    }

    @Test
    fun `when task exists and belongs to user, save the task`() {
        val task = Task(1, "", "", "Open", arrayListOf(User(5, arrayListOf())))
        val tasks = mock<TaskRepository> {
            whenever(it.findById(1)).thenReturn(Optional.of(task))
        }
        val users = mock<UserRepository> { }
        val controller = RepoTaskController(tasks, users)

        controller.closeTaskById(1, 5)

        verify(tasks, times(1)).findById(1)
        verify(tasks, times(1)).saveAndFlush(Mockito.argThat {
            it.status == "CLOSED"
        })
        verifyNoMoreInteractions(users)
    }
}