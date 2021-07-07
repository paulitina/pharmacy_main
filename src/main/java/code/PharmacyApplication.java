package code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "code/entities")
@EnableJpaRepositories(basePackages = "code.repositories")
public class PharmacyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PharmacyApplication.class, args);
    }
}
