package b4e.ru.mtp.backend.ms.user.service;


import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class RequestContextService {

    public static String MDC_USER_ID = "user_id";

    public void setUserId(String userId) {
        MDC.put(MDC_USER_ID, userId);
    }

}
