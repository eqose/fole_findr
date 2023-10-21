package findr.fole;

import findr.fole.service.ContractService;
import findr.fole.service.StudentService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class FoleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoleBackendApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(StudentService studentService, ContractService contractService) {
		return args -> {
			System.out.println(studentService.findAll());
			System.out.println(contractService.findAllByStatus(true));
		};
	}

}
