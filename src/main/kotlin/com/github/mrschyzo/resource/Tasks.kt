package com.github.mrschyzo.resource

import com.github.mrschyzo.controller.TaskController
import org.springframework.web.bind.annotation.*
import com.github.mrschyzo.dto.input.Task as InTask

@RestController
class Tasks(
        private val taskController: TaskController
) {
    @GetMapping("/user/{userId}/tasks")
    @ResponseBody
    fun getTasksByUser(@PathVariable userId : Int) = taskController.getTasksByUser(userId)

    @PostMapping("/tasks")
    fun createTask(@RequestBody task : InTask) : Int = taskController.createTask(task)

    @GetMapping("/tasks/{id}")
    @ResponseBody
    fun getTaskById(@PathVariable id : Int) = taskController.getTaskById(id)

    @PutMapping("/tasks/{id}")
    fun closeTaskById(@PathVariable id : Int) = taskController.closeTaskById(id)
}