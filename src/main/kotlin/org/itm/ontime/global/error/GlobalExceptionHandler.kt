package org.itm.ontime.global.error

import ErrorResponse
import org.itm.ontime.application.auth.exception.common.AuthErrorCode
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// TODO : 중복 로직 제거하는 방법 찾아보고 리팩토링하기

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(
        exception: BusinessException
    ): ResponseEntity<ErrorResponse> {

        log.error("handleBusinessException", exception)

        return ResponseEntity
            .status(exception.errorCode.status)
            .body(ErrorResponse.of(exception.errorCode))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {

        log.error("handleMethodArgumentNotValidException", exception)

        val bindingResult = exception.bindingResult

        val errors = bindingResult.fieldErrors.map { fieldError ->
            ErrorResponse.FieldError(
                field = fieldError.field,
                value = fieldError.rejectedValue,
                reason = fieldError.defaultMessage ?: "Invalid value"
            )
        }

        return ResponseEntity
            .status(CommonErrorCode.INVALID_INPUT_VALUE.status)
            .body(ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, errors))
    }

    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(
        exception: AccessDeniedException
    ): ResponseEntity<ErrorResponse> {

        log.error("handleAccessDeniedException", exception)

        return ResponseEntity
            .status(AuthErrorCode.UNAUTHORIZED.status)
            .body(ErrorResponse.of(AuthErrorCode.UNAUTHORIZED))
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(
        exception: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ErrorResponse> {

        log.error("handleHttpRequestMethodNotSupportedException", exception)

        return ResponseEntity
            .status(CommonErrorCode.METHOD_NOT_ALLOWED.status)
            .body(ErrorResponse.of(CommonErrorCode.METHOD_NOT_ALLOWED))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(
        exception: Exception
    ): ResponseEntity<ErrorResponse> {

        log.error("handleException", exception)

        return ResponseEntity
            .status(CommonErrorCode.INTERNAL_SERVER_ERROR.status)
            .body(ErrorResponse.of(CommonErrorCode.INTERNAL_SERVER_ERROR))
    }

}