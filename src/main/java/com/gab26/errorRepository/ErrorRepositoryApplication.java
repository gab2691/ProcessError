package com.gab26.errorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ErrorRepositoryApplication extends SpringBootServletInitializer {
    public ErrorRepositoryApplication() {
    }
    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[]{ErrorRepositoryApplication.class});
    }
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(new Class[]{ErrorRepositoryApplication.class}).headless(false).run(args);;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
