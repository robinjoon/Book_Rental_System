package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import services.BookService;
import services.MemberService;
import services.RentalService;

@Configuration
public class ServiceConfig {
	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	@Bean
	public BookService bookService() {
		return new BookService();
	}
	@Bean
	public RentalService rentalService() {
		return new RentalService();
	}
}
