package com.github.mrschyzo.model

import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy=GenerationType.AUTO) val id: Int,
    @ManyToMany(mappedBy="assignees")
    val assignedTasks: List<Task>
)