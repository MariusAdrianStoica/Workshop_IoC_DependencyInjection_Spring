package se.lexicon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages ="se.lexicon")
public class ComponentScanConfig {

    @Bean //if you want to add a framework outside the main app
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}
