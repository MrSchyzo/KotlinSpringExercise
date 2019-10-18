package com.github.mrschyzo.controller

import com.github.mrschyzo.dto.input.Task as InTask
import com.github.mrschyzo.dto.output.Task as OutTask

interface TaskController {
    fun getTasksByUser(userId : Int) : List<Int>
    fun createTask(task : InTask) : Int
    fun getTaskById(id : Int) : OutTask
    fun closeTaskById(id : Int)
}
