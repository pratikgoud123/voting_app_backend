package com.example.voting_app;

//import com.example.voting_app.filter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VotingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingAppApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean jwtFilter()
//	{
//		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new Filter());
//		filterRegistrationBean.addUrlPatterns("/api/v1/*");
//
//		return filterRegistrationBean;
//	}

}
