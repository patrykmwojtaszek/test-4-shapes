package pl.kurs.test4shapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Test4ShapesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test4ShapesApplication.class, args);
    }

}
