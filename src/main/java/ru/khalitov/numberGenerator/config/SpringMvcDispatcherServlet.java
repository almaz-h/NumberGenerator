package ru.khalitov.numberGenerator.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Класс реализует интерфейс WebApplicationInitializer, для считывания сервером Apache Tomcat,
 * который конфигурирует DispatcherServlet, это замена файла web.xml
 */
public class SpringMvcDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
