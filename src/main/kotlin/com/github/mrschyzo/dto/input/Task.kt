package com.github.mrschyzo.dto.input

data class Task (
    val name: String,
    val description: String,
    val users: List<Int>
)