package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controllers.BookController;
import controllers.MemberController;
import controllers.RentalController;


@Configuration
public class ControllerConfig {
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
	@Bean
	public BookController bookController() {
		return new BookController();
	}
	@Bean
	public RentalController rentalController() {
		return new RentalController();
	}
}
