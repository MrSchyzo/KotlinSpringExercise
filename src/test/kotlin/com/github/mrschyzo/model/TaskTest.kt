package com.github.mrschyzo.model

import org.junit.Test

import org.junit.Assert.*

class TaskTest {
    @Test
    fun `when task has not any user return false`() {
        val task = Task(id = 0, name = "", description = "", status = "", assignees = ArrayList<User>())

        assertFalse("User 9 is not owner of a Task without users", task.isOwnedByUserId(9))
    }

    @Test
    fun `when task has the given user return true`() {
        val task = Task(id = 0, name = "", description = "", status = "", assignees = arrayListOf(
                User(5, ArrayList<Task>()),
                User(6, ArrayList<Task>())
        ))

        assertTrue("User 5 is owner of a Task with users (5, 6)", task.isOwnedByUserId(5))
    }

    @Test
    fun `when task has the given user more than once return true`() {
        val task = Task(id = 0, name = "", description = "", status = "", assignees = arrayListOf(
                User(3, ArrayList<Task>()),
                User(4, ArrayList<Task>()),
                User(3, ArrayList<Task>())
        ))

        assertTrue("User 3 is owner of a Task with users (3, 4, 3)", task.isOwnedByUserId(3))
    }

    @Test
    fun `when task has not the given user return false`() {
        val task = Task(id = 0, name = "", description = "", status = "", assignees = arrayListOf(
                User(5, ArrayList<Task>()),
                User(4, ArrayList<Task>()),
                User(3, ArrayList<Task>())
        ))

        assertFalse("User 6 is not owner of a Task with users (5, 4, 3)", task.isOwnedByUserId(6))
    }
}