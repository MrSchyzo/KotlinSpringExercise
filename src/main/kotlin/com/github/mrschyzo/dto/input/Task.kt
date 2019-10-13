package com.github.mrschyzo.dto.input

data class Task (
    val name: String,
    val description: String,
    val status: String,
    val users: List<Int>
)