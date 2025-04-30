package acc.br.summerAcademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class CashbackApplication {
	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}

}
