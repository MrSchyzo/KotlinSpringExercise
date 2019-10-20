package com.github.mrschyzo.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    val id: Int,

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
) {
    fun isOwnedByUserId(userId: Int) : Boolean = this.assignees.map { it.id }.contains(userId)
}