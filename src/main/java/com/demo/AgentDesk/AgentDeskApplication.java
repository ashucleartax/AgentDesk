package com.demo.AgentDesk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AgentDeskApplication {

	public static void main(String[] args) {
		final SpringApplication application = new SpringApplication(AgentDeskApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
//		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}
}
