package acc.br.summerAcademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
		"acc.br.summerAcademy.model",
		"acc.br.summerAcademy.domain.model"})
@EnableJpaRepositories("acc.br.summerAcademy.repository")
public class CashbackApplication {
	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}

}
