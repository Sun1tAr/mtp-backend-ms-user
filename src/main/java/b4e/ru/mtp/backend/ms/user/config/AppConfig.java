package b4e.ru.mtp.backend.ms.user.config;

import b4e.ru.mtp.backend.commons.filter.MtpExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MtpExceptionHandler.class)
public class AppConfig {

}
