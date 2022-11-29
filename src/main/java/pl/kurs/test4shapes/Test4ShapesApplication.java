package pl.kurs.test4shapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Test4ShapesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test4ShapesApplication.class, args);
    }

}
