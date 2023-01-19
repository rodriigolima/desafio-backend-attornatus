package br.com.desafio.attornatus.expection;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ExceptionError.Propriedade> campos = new ArrayList<>();
        for(ObjectError err: ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) err).getField();
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            
            campos.add(new ExceptionError.Propriedade(nome, mensagem));
        }
        
        ExceptionError err = new ExceptionError();
        err.setStatus(status.value());
        err.setDataHora(LocalDateTime.now());
        err.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento corretamente.");
        err.setCampos(campos);
        
        return handleExceptionInternal(ex, err, headers, status, request);
    }
}