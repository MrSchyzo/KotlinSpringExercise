package com.github.mrschyzo.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    val id: Int,

    @ManyToMany(mappedBy="assignees")
    val assignedTasks: List<Task>
)