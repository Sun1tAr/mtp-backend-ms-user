package b4e.ru.mtp.backend.ms.user.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;


@Getter
public class MtpException extends RuntimeException implements Supplier<MtpException> {

    private final MtpErrorCode code;

    @Setter
    private Object data;

    public MtpException(MtpErrorCode code) {
        this.code = code;
    }

    public MtpException(MtpErrorCode code, Object data) {
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return code.getMessage();
    }

    @Override
    public MtpException get() {
        return this;
    }

}
