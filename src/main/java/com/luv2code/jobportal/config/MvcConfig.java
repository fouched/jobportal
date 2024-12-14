package com.luv2code.jobportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String UPLOAD_DIR = "photos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(registry);
    }

    private void exposeDirectory(ResourceHandlerRegistry registry) {
        Path path = Paths.get(MvcConfig.UPLOAD_DIR);
        registry.addResourceHandler("/" + MvcConfig.UPLOAD_DIR + "/**").addResourceLocations("file:" + path.toAbsolutePath() + "/");
    }
}
