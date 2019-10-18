package com.github.mrschyzo.controller

import com.github.mrschyzo.model.Task
import com.github.mrschyzo.repository.TaskRepository
import com.github.mrschyzo.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.*
import com.github.mrschyzo.dto.input.Task as InTask
import com.github.mrschyzo.dto.output.Task as OutTask

@Component
class RepoTaskController(
        private val tasks: TaskRepository,
        private val users: UserRepository
) : TaskController {
    override fun getTasksByUser(userId: Int): List<Int> =
            tasks.findAll().filter {
                it.assignees.map { x -> x.id }.contains(userId)
            }.map{ it.id }

    override fun createTask(task: InTask): Int =
            Optional.of(task).map {
                Task(0, it.name, it.description, "CREATED", it.users.map { u -> users.findById(u) }.map { x -> x.get() })
            }.map {
                tasks.save(it)
            }.map {
                it.id
            }.orElseThrow {
                RuntimeException()
            }

    override fun getTaskById(id: Int): OutTask =
            tasks.findById(id).map {
                OutTask(it.id, it.name, it.description, it.status, it.assignees.map { x -> x.id })
            }.orElseThrow {
                RuntimeException()
            }

    override fun closeTaskById(id: Int) {
        tasks.findById(id).map {
            Task(it.id, it.name, it.description, "CLOSED", it.assignees)
        }.map {
            tasks.saveAndFlush(it)
        }.orElseThrow {
            RuntimeException()
        }
    }
}
