package com.github.mrschyzo.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TaskDoesNotBelongToUserException(message: String) : RuntimeException(message)