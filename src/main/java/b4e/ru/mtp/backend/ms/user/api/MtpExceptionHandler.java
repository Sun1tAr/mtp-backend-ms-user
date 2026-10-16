package b4e.ru.mtp.backend.ms.user.api;

import b4e.ru.mtp.backend.ms.user.api.dto.ErrorDtoResponse;
import b4e.ru.mtp.backend.ms.user.exception.MtpErrorCode;
import b4e.ru.mtp.backend.ms.user.exception.MtpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class MtpExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDtoResponse> defaultHandler(Exception e) {
        log.error("Поймано неопределенное исключение: {}", e.getMessage());
        return handleMtpException(new MtpException(MtpErrorCode.INTERNAL_ERROR, e.getMessage()));
    }

    @ExceptionHandler(MtpException.class)
    public ResponseEntity<ErrorDtoResponse> handleMtpException(MtpException e) {

        ErrorDtoResponse errorDto = new ErrorDtoResponse();
        errorDto.setMessage(e.getMessage());
        errorDto.setCode(e.getCode());
        errorDto.setData(e.getData());

        if (e.getCode().getHttpStatus().is5xxServerError()) {
            log.error("Возникла системная ошибка: code = [{}], status = [{}], message = [{}], data = [{}]",
                    e.getCode().name(), e.getCode().getHttpStatus().value(), e.getCode().getMessage(), e.getData()
            );
            errorDto.setData("");
        }
        return new ResponseEntity<>(errorDto, e.getCode().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDtoResponse> handleValidationException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);

        return handleMtpException(new MtpException(MtpErrorCode.REQUEST_VALIDATION_ERROR, body));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDtoResponse> handleNullPointerException(NullPointerException e) {
        return handleMtpException(new MtpException(MtpErrorCode.BAD_REQUEST, e.getMessage()));
    }

}
