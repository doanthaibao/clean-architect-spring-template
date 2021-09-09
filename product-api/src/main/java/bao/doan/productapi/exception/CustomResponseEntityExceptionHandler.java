package bao.doan.productapi.exception;

import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.EntityNotFoundException;
import bao.doan.productusecase.exception.ErrorDetail;
import java.util.Collections;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Generated
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllException(
      final Exception ex,
      final WebRequest request) {
    final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .error(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .errorDetails(Collections.singletonList(new ErrorDetail(null, ex.getMessage())))
        .path(request.getDescription(false))
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .build();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(
      final EntityNotFoundException entityNotFoundException,
      final WebRequest request) {
    final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .error(HttpStatus.NOT_FOUND.toString())
        .errorDetails(entityNotFoundException.getErrorDetails())
        .path(request.getDescription(false))
        .code(HttpStatus.NOT_FOUND.value())
        .build();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityAlreadyExistException.class)
  public final ResponseEntity<ExceptionResponse> handleEntityAlreadyExistException(
      final EntityAlreadyExistException entityAlreadyExistException,
      final WebRequest request) {
    final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .error(HttpStatus.CONFLICT.toString())
        .errorDetails(entityAlreadyExistException.getErrorDetails())
        .path(request.getDescription(false))
        .code(HttpStatus.CONFLICT.value())
        .build();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }

}
