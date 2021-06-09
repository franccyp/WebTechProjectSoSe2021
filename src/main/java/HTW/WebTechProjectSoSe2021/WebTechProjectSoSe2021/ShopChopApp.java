package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class ShopChopApp {

	public static void main(String[] args) {

		SpringApplication.run(ShopChopApp.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}





