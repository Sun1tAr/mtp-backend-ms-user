package b4e.ru.mtp.backend.ms.user.exception;

import b4e.ru.mtp.backend.ms.user.api.MtpHttpHeaders;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MtpErrorCode {

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Произошла непредвиденная ошибка"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Переданы невалидные данные"),
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Данные не соответствуют политике валидации"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Указанный счет не найден"),
    HEADER_USER_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "Заголовок " + MtpHttpHeaders.USER_ID + " не найден"),
    HEADER_USER_ID_INVALID(HttpStatus.BAD_REQUEST, "Заголовок " + MtpHttpHeaders.USER_ID + " содержит некорректные данные"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "Пользователь с такими данными уже существует"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Пользователь с такими данными не существует"),
    CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка работы с клиентом"),
    CLIENT_SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Вызываемый клиент недоступен"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Категория не найдена"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Заявка на перемещение средств не найдена"),
    TRANSACTION_TYPE_INVALID(HttpStatus.BAD_REQUEST, "Указанный тип транзакции не поддерживается"),
    TRANSACTION_STATUS_INVALID(HttpStatus.INTERNAL_SERVER_ERROR, "Указанный статус транзакции не найден"),
    TRANSACTION_AMOUNT_INVALID(HttpStatus.BAD_REQUEST, "Сумма транзакции должна быть положительна"),
    ORDER_STATUS_INVALID(HttpStatus.INTERNAL_SERVER_ERROR, "Указанный статус заявки на перемещение средств не найден"),
    AUTHENTICATION_JWT_INVALID(HttpStatus.UNAUTHORIZED, "Токен аутентификации поврежден"),
    AUTHENTICATION_USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Пользователь не найден"),
    AUTHENTICATION_BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Неверные данные для входа"),


    CLIENT_OPERATION_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "Вызванная пользователем операция не поддерживается"),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    MtpErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
