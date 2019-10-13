package com.github.mrschyzo.resource

import com.github.mrschyzo.dto.input.Task as InTask
import com.github.mrschyzo.dto.output.Task as OutTask
import org.springframework.web.bind.annotation.*

@RestController
class Tasks {
    @GetMapping("/user/{userId}/tasks")
    @ResponseBody
    fun getTasksByUser(@PathVariable userId : String) = listOf(1, 2)

    @PostMapping("/tasks")
    fun createTask(@RequestBody task : InTask) : Int {
        println(task)
        return 1
    }

    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable id : Int) = OutTask(id, "Task$id", "This is task with id $id", "Done", listOf(1,2,3,4))

    @PutMapping("/tasks/{id}")
    fun closeTaskById(@PathVariable id : Int) = println("Closing task $id")
}