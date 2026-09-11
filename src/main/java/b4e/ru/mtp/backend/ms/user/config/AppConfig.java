package b4e.ru.mtp.backend.ms.user.config;

import b4e.ru.mtp.backend.commons.config.FilterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {FilterConfig.class})
public class AppConfig {

}
