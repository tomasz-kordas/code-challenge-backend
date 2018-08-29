package com.tkordas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AlmostTwitterSpringBootWebApp extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(AlmostTwitterSpringBootWebApp.class);
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(AlmostTwitterSpringBootWebApp.class, args);
  }

}
