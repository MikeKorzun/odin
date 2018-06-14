package com.setplex.odin.configuration;


import com.setplex.odin.provider.config.ProviderConfig;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MwWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                MwSecurityConfiguration.class,
                MwWebConfiguration.class,
                ProviderConfig.class
              };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("", 2097152, 2306868, 0));
    }
}
