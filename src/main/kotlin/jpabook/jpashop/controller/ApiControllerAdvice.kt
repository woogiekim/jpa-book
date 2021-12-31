package jpabook.jpashop.controller

import jpabook.jpashop.exception.JpaBookException
import mu.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.Locale

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice(
    private val messageSource: MessageSource
) {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        logger.error(e) { e.message }
        throw e
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JpaBookException::class)
    fun handleJpaBookException(e: JpaBookException): ErrorResponse {
        logger.error { "JpaBookException: ${e.errorCode.name}(${e.errorCode.code}, ${e.errorCode.message}), ${e.message}" }

        return ErrorResponse(
            e.errorCode.code,
            messageSource.getMessage(e.errorCode.message, null, Locale.getDefault())
        )
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String
)
