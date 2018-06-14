/*
package com.setplex.odin.configuration;

import java.util.List;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.setplex.odin.controller")
@EnableAspectJAutoProxy
@EnableWebMvc
public class MwWebConfiguration extends WebMvcConfigurerAdapter {

    private final MappingJackson2HttpMessageConverter jacksonConverter;

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, jacksonConverter);
    }

    public MwWebConfiguration(@NonNull MappingJackson2HttpMessageConverter jacksonConverter) {
        this.jacksonConverter = jacksonConverter;
    }
}
*/
