package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("web")
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);//не обязательно поскольку по умолчанию
        //04.06
        templateResolver.setCharacterEncoding("UTF-8");
        //04.06
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        // 04.06
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        // 04.06
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);

    }

    /*
    Добавил 4.06
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//
        //Перехватчик, который позволяет изменять текущую локаль при каждом запросе через настраиваемый параметр запроса (имя параметра по умолчанию: «локаль»).
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();//в конфигурации этого перехватчика определяется
        // параметр "параметр" для смены региональных настроек веб приложения
        interceptor.setParamName("locale");//Задайте имя параметра, который содержит спецификацию локали в запросе на изменение локали
        registry.addInterceptor(interceptor);//
    }

    @Bean
    //загрузка сообщений из определенных файлов предназначенных для интернационализации
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("WEB-INF/locales/cars");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);//если специальный компонент не обнаружен - вернуться по уполчанию
        return messageSource;
    }

    @Bean
        //хранение и извлечение региоанальных настроек из браузера
    CookieLocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);//Установите фиксированный языковой стандарт, который будет возвращать этот распознаватель, если cookie не найден.
        cookieLocaleResolver.setCookieMaxAge(3600);
        cookieLocaleResolver.setCookieName("locale");//Используйте данное имя для файлов cookie, созданных этим генератором.
        return cookieLocaleResolver;
    }
}