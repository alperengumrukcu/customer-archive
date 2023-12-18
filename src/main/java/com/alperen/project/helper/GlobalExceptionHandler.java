package com.alperen.project.helper;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.model.exception.notfound.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.NoSuchFileException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.NoSuchElementException;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        BaseResponse<?> baseResponse = new BaseResponse<>();
        for (ObjectError e: exception.getBindingResult().getAllErrors()) {
            baseResponse.setMessage(e.getDefaultMessage());
        }
        baseResponse.setSuccess(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<BaseResponse<?>> notFoundException(NotFoundException exception, Locale locale){
        return BaseResponse.error(messageSource.getMessage(exception.getMessage(),null,locale), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<BaseResponse<?>> dateTimeException(DateTimeException exception){
        return BaseResponse.error("Tarih formatı hatalı!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<BaseResponse<?>> noSuchElementException(NoSuchElementException exception){
        return BaseResponse.error("Dosya bulunamadı.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<BaseResponse<?>> maxUploadSizeExceededException(MaxUploadSizeExceededException exception){
        return BaseResponse.error("Dosya boyutunuz 10MB geçmemelidir!",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchFileException.class)
    public ResponseEntity<BaseResponse<?>> noSuchFileException(NoSuchFileException exception){
        return BaseResponse.error("Dosya bulunamadı veya silinmiş olabilir.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<BaseResponse<?>> runtimeException(RuntimeException exception){
        return BaseResponse.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
