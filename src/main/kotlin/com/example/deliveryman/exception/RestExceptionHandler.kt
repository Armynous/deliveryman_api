package com.example.deliveryman.exception

import com.example.deliveryman.response.ErrorResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(NotFoundException::class)])
    protected fun notFoundException(
        ex: NotFoundException
    ): ResponseEntity<Any> {
        val apiError = ErrorResponseMessage(errorMessage = ex.message ?: "Notfound")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
    }

    @ExceptionHandler(value = [(BadRequestException::class)])
    protected fun badRequestException(
        ex: BadRequestException
    ): ResponseEntity<Any> {
        val apiError = ErrorResponseMessage(errorMessage = ex.message ?: "BadRequest")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError)
    }
}