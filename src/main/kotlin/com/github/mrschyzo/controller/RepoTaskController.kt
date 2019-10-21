package com.github.mrschyzo.controller

import com.github.mrschyzo.error.TaskDoesNotBelongToUserException
import com.github.mrschyzo.error.TaskNotFoundException
import com.github.mrschyzo.error.UserNotFoundException
import com.github.mrschyzo.model.Task
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional
import com.github.mrschyzo.dto.input.Task as InTask
import com.github.mrschyzo.dto.output.Task as OutTask

@Component
open class RepoTaskController(
        private val tasks: TaskRepository,
        private val users: UserRepository
) : TaskController {
    @Transactional
    override fun getTasksByUser(userId: Int): List<Int> = tasks.getTasksByUserId(userId).map{it.id}

    @Transactional
    override fun createTask(task: InTask): Int {
        val users = task.users.map { users.findById(it) }
        users.stream()
                .filter{ ! it.isPresent }
                .findFirst()
                .flatMap { it }
                .map { UserNotFoundException("User with id ${it.id} not found") }
                .ifPresent { throw it }

        return tasks.save(Task(0, task.name, task.description, "OPEN", users.map{maybe -> maybe.get()})).id
    }

    @Transactional
    override fun getTaskById(id: Int): OutTask =
            tasks.findById(id).map {
                OutTask(it.id, it.name, it.description, it.status, it.assignees.map { x -> x.id })
            }.orElseThrow {
                TaskNotFoundException("Task with id $id not found")
            }

    @Transactional
    override fun closeTaskById(id: Int, userId: Int) {
        val task = tasks.findById(id).map {
            Task(it.id, it.name, it.description, "CLOSED", it.assignees)
        }.orElseThrow {
            TaskNotFoundException("There is not any task with id $id")
        }

        if (!task.isOwnedByUserId(userId))
            throw TaskDoesNotBelongToUserException("Task $id does not belong to user $userId")

        tasks.saveAndFlush(task)
    }
}
