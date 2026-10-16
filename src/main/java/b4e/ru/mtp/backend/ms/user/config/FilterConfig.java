package b4e.ru.mtp.backend.ms.user.config;


import b4e.ru.mtp.backend.ms.user.api.MtpExceptionHandler;
import b4e.ru.mtp.backend.ms.user.api.RequestInterceptor;
import b4e.ru.mtp.backend.ms.user.service.RequestContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = {MtpExceptionHandler.class, RequestContextService.class})
public class FilterConfig implements WebMvcConfigurer {

    private final RequestInterceptor requestInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }

}
