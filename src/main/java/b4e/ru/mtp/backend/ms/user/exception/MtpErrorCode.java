package b4e.ru.mtp.backend.ms.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MtpErrorCode {

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Произошла непредвиденная ошибка"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Переданы невалидные данные"),
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Данные не соответствуют политике валидации"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "Пользователь с такими данными уже существует"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Пользователь с такими данными не существует"),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    MtpErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
