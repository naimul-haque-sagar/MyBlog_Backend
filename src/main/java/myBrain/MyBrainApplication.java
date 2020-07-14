package myBrain;

import myBrain.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class MyBrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBrainApplication.class, args);
	}

}
