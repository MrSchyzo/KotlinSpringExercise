package com.github.mrschyzo.repository

import com.github.mrschyzo.model.Task
import com.github.mrschyzo.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>