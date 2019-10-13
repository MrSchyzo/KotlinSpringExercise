package com.github.mrschyzo.dto.output

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val status: String,
    val users: List<Int>
)