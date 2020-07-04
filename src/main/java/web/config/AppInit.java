package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
//                WebConfigSpring.class
        };

    }


    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //Укажите фильтры для добавления и сопоставления с DispatcherServlet
    @Override
    protected Filter[] getServletFilters() {
        /*CharacterEncodingFilter
        Фильтр сервлетов, позволяющий задавать кодировку символов для запросов.
        Это полезно, потому что современные браузеры обычно не устанавливают кодировку символов,
        даже если она указана на странице HTML или в форме.
        Этот фильтр может применить свою кодировку, если в запросе еще не указана кодировка,
        или принудительно применить кодировку этого фильтра в любом случае ("forceEncoding" = "true").
        В последнем случае кодировка также будет применяться как кодировка ответа по умолчанию
        (хотя обычно она будет переопределена полным типом содержимого, установленным в представлении).
         */
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");//Set the encoding to use for requests.
        characterEncodingFilter.setForceEncoding(true);//Set whether the configured encoding of this filter is supposed to override existing request and response encodings.Установите, должна ли настроенная кодировка этого фильтра переопределять существующие кодировки запросов и ответов
        return new Filter[]{characterEncodingFilter};//
    }


}