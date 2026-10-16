package b4e.ru.mtp.backend.ms.user.api;

import b4e.ru.mtp.backend.ms.user.service.RequestContextService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final RequestContextService requestContextService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader(MtpHttpHeaders.USER_ID);
        requestContextService.setUserId(userId);
        log.info("Начало выполнения запроса [{} {}]", request.getMethod(), request.getRequestURI());
        log.debug("Запрос: method = [{}] uri = [{}] userId = [{}] params = [{}]",
                request.getMethod(), request.getRequestURI(), userId, request.getQueryString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Конец выполнения запроса [{} {}], status = [{}]", request.getMethod(), request.getRequestURI(), response.getStatus());
        MDC.clear();
    }

}
