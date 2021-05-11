package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShopChopApp {

	public static void main(String[] args) {

		SpringApplication.run(ShopChopApp.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
}





