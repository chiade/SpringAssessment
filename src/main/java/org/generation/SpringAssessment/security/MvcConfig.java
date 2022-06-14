package org.generation.SpringAssessment.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.file.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // cos' we have implemented WebMvcConfigurer, so we can do our own implementation of the method on the WebMvcConfigure interface

    @Value("${image.folder}")
    private String imageFolder; //now imageFolder variable the value = productimages

    public void addViewControllers(ViewControllerRegistry registry) {
        //Map the browser's URL to a specific view (html) inside resources/templates directory
        // we can register view that create a direct mapping between the URL and view name (html)
        registry.addViewController("/").setViewName("products");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/productform").setViewName("productform");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //expose the images, is, css resources to the client (browser) so that they can access the necessary files to display
        registry.addResourceHandler("/static")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);

        //expose the productImages folder to allow external client to access the files from the server
        Path uploadDir = Paths.get(imageFolder);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + imageFolder + "/**")
                .addResourceLocations("file:" + uploadPath + "/")
                .setCachePeriod(0);
    }
}



