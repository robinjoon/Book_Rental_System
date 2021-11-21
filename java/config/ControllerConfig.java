package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controllers.MemberController;


@Configuration
public class ControllerConfig {
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
}
