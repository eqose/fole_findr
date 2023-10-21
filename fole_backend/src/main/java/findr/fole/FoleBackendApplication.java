package findr.fole;

import findr.fole.service.StudentService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoleBackendApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(StudentService studentService) {
		return args -> {
			System.out.println(studentService.findAll());
		};
	}

}
