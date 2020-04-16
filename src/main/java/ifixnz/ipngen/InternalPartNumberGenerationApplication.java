package ifixnz.ipngen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {
        "ifixnz.ipngen.controller",
        "ifixnz.ipngen.service",
        "ifixnz.ipngen"})
@PropertySource(value = {
        "application.properties"})
public class InternalPartNumberGenerationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(InternalPartNumberGenerationApplication.class, args);
    }
}
