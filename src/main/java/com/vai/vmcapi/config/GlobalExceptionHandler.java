package com.vai.vmcapi.config;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.exception.BusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    ResponseEntity<ResponseDTO> handleAuthenticationException(AuthenticationException ex) {
//        userErrorLogger.error(ex);
        return new ResponseEntity<>(new ResponseDTO(401, ex.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
//        ex.printStackTrace();
//        userErrorLogger.error(ex);
        return getInvalidArg();
    }

    ResponseEntity getInvalidArg(){
        return new ResponseEntity<>(new ResponseDTO(400, "Dữ liệu đầu vào không hợp lệ"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    ResponseEntity<ResponseDTO> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        return new ResponseEntity<>(new ResponseDTO(410, ex.getMessage()),
                HttpStatus.GONE);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    ResponseEntity<ResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(new ResponseDTO(415, "Method not allow"),
                HttpStatus.GONE);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
//        userErrorLogger.error(ex);

        return new ResponseEntity<>(new ResponseDTO(403,
                "Bạn không có quyền try cập tài nguyên này"),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {InvalidDefinitionException.class})
    ResponseEntity<ResponseDTO> handleInvalidDefinitionException(InvalidDefinitionException ex) {
//        userErrorLogger.error(ex);

        return getInvalidArg();
    }


    @ExceptionHandler(value = {BusinessException.class})
    ResponseEntity<ResponseDTO> handleDomainException(BusinessException ex) {
//        userErrorLogger.error(ex);

        return new ResponseEntity<>(new ResponseDTO(ex.getCode(),
                ex.getMessage()),
                ex.getHttpStatus()
        );
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
            JpaSystemException.class,
            InvalidDataAccessApiUsageException.class,
            PropertyReferenceException.class})
    ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(Exception ex) {
//        userErrorLogger.error(ex);

        return getInvalidArg();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseDTO> logRuntimeException(RuntimeException runtimeException) {
//        systemErrorLogger.error(runtimeException);
        runtimeException.printStackTrace();


        return new ResponseEntity<>(new ResponseDTO(500,
                runtimeException.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<ResponseDTO> logRuntimeException() {
////        systemErrorLogger.error(runtimeException);
//
//
//        return new ResponseEntity<>(new ResponseDTO(500,
//                "Internal Server"),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
//        userErrorLogger.error(ex);

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String> messageErrors = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return new ResponseEntity<>(new ResponseDTO(4004,
                String.join(", ", messageErrors)),
                HttpStatus.BAD_REQUEST);
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
//        userErrorLogger.error(ex);

        List<String> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return new ResponseEntity<>(new ResponseDTO<>(
                4004,
                String.join(", ", errorMessages)),
                HttpStatus.BAD_REQUEST);
    }
}