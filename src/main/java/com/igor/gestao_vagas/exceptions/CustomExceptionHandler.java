package com.igor.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// classe controladora para tratar exceções, é acionada quando uma exceção é lançada
@ControllerAdvice
public class CustomExceptionHandler {

    private MessageSource messageSource;
    
    public CustomExceptionHandler(MessageSource message){
        this.messageSource = message;
    }
    
    // quando validação de argumento com anotação @Valid falha, essa exceção é lançada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            dto.add(new ErrorMessageDTO(message, err.getField()));
        });

        return ResponseEntity.badRequest().body(dto);
    }
}
