package com.github.mrschyzo.model

import javax.persistence.*

@Entity
class Task(
    @Id @GeneratedValue(strategy=GenerationType.AUTO) val id: Int,
    val name: String,
    val description: String,
    val status: String,
    @ManyToMany
    @JoinTable(
            name = "course_like",
            joinColumns = [JoinColumn(name = "task_id")],
            inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val assignees: List<User>
)